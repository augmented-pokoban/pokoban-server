package server.services

import server.exceptions.ImpossibleActionException
import server.model.*
import server.model.objects.Agent
import server.repositories.FileRepository
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

/**
 * Singleton PokobanService which holds getPage current games in-memory
 */
class PokobanService private constructor() {
    private object Holder {
        val INSTANCE = PokobanService()
    }

    companion object {
        val instance: PokobanService by lazy { Holder.INSTANCE }
    }

    // Transitions in games currently being played
    private val transitions: MutableMap<String, Stack<PokobanTransition>> = HashMap()

    // Initial states for getPage current games
    private val initialStates: MutableMap<String, PokobanState> = HashMap()

    // Games currently being played
    private val games: MutableMap<String, Pokoban> = HashMap()

    /**
     * Start a new game
     */
    fun start(levelName: String): Pokoban {
        val file = FileRepository().getLevel(levelName)
        val level = LevelService.instance.loadLevel(file, levelName)
        val gameId = UUID.randomUUID().toString()
        val newGame = Pokoban(gameId, level)

        instance.initialStates.put(gameId, newGame.getState())
        instance.games.put(gameId, newGame)
        instance.transitions[gameId] = Stack()

        return newGame
    }

    /**
     * Copies the state of an active game, into a new game
     */
    fun copy(id: String): Pokoban {
        val copyId = UUID.randomUUID().toString()
        val copy = Pokoban(copyId, get(id).level.copy())

        instance.initialStates.put(copyId, copy.getState())
        instance.games.put(copyId, copy)
        instance.transitions[copyId] = Stack()
        return copy
    }

    /**
     * Returns given game
     */
    fun get(id: String): Pokoban = instance.games.getValue(id)

    /**
     * Removes given game and it's associated transitions
     */
    fun remove(id: String): Triple<PokobanState?, Pokoban?, MutableList<PokobanTransition>?> = Triple(
            instance.initialStates.remove(id),
            instance.games.remove(id),
            instance.transitions.remove(id)
    )

    /**
     * Returns getPage games
     */
    fun all(): List<Pokoban> = instance.games.values.toList()

    /**
     * Transitions given game into a new state
     * Returns (reward, game)
     */
    fun transition(gameId: String, action: PokobanAction): Triple<Boolean, Number, Pokoban> {

        var game = instance.get(gameId)
        val agent: Agent = game.level.getAgents().first() // We assume only 1 agent
        val solvedGoalsBefore = game.numberOfSolvedGoals()

        var success = true
        try {
            game = when (action) { // kotlin is awesome
                PokobanAction.MOVE_NORTH -> moveOrPush(game, agent, Direction.NORTH)
                PokobanAction.MOVE_SOUTH -> moveOrPush(game, agent, Direction.SOUTH)
                PokobanAction.MOVE_EAST -> moveOrPush(game, agent, Direction.EAST)
                PokobanAction.MOVE_WEST -> moveOrPush(game, agent, Direction.WEST)
                PokobanAction.PULL_NORTH -> pull(game, agent, Direction.NORTH)
                PokobanAction.PULL_SOUTH -> pull(game, agent, Direction.SOUTH)
                PokobanAction.PULL_EAST -> pull(game, agent, Direction.EAST)
                PokobanAction.PULL_WEST -> pull(game, agent, Direction.WEST)
            }
        } catch (e: ImpossibleActionException) {
            success = false
        }

        // calculate reward
        val solvedGoalsAfter = game.numberOfSolvedGoals()
        val reward: Number = when {
            solvedGoalsBefore > solvedGoalsAfter -> -1.0 // we suck!
            solvedGoalsBefore < solvedGoalsAfter -> 1.0 // we solved a goal!
            !success -> -0.1 // impossible action
            else -> -0.1
        }

        // Store this transition
        val transition = PokobanTransition(reward, success, game.isDone(), action, game.getState())
        instance.store(gameId, transition)

        // update the game in singleton instance
        instance.update(gameId, game)

        return Triple(success, reward, game)
    }

    /**
     * Stores a new transition associated with given game id
     */
    private fun store(id: String, transition: PokobanTransition) = instance.transitions[id]!!.add(transition)

    /**
     * Updates given game in hashmap
     */
    private fun update(id: String, game: Pokoban) = instance.games.put(id, game)

    /**
     * Checks if a Push-action is applicable or else it applies the move action
     */
    private fun moveOrPush(game: Pokoban, agent: Agent, direction: Direction): Pokoban {
        val (boxX, boxY) = getRelativePosition(game.level.get(agent), direction)
        return when {
            game.level.get(boxX, boxY) != null -> push(game, agent, direction)
            else -> move(game, agent, direction)
        }
    }

    /**
     * Moves the agent 1 step in given direction - if possible
     * Returns the game with an updated state
     */
    private fun move(game: Pokoban, agent: Agent, direction: Direction): Pokoban {
        val (newX, newY) = getRelativePosition(game.level.get(agent), direction)

        if (!isValidPosition(game, newX, newY)) throw ImpossibleActionException("Impossible move action.")

        // Perform the move
        game.level.update(agent, newX, newY)

        return game
    }

    /**
     * Pushes a box in given direction, with agent
     */
    private fun push(game: Pokoban, agent: Agent, direction: Direction): Pokoban {
        // find the box
        val (boxX, boxY) = getRelativePosition(game.level.get(agent), direction)
        val box = game.level.get(boxX, boxY) ?: throw ImpossibleActionException("Impossible push action.")
        val (boxNewX, boxNewY) = getRelativePosition(boxX, boxY, direction)

        if (!isValidPosition(game, boxNewX, boxNewY)) throw ImpossibleActionException("Impossible push action.")

        // move the box
        game.level.update(box, boxNewX, boxNewY)
        // move the agent into the box's old position
        game.level.update(agent, boxX, boxY)

        return game
    }

    /**
     * Pulls a box in given directoin, with agent
     */
    private fun pull(game: Pokoban, agent: Agent, direction: Direction): Pokoban {
        // find the box
        val (agentX, agentY) = game.level.get(agent)
        val (agentNewX, agentNewY) = getRelativePosition(agentX, agentY, direction)

        val (boxX, boxY) = getRelativePosition(game.level.get(agent), direction.inverse())
        val box = game.level.get(boxX, boxY) ?: throw ImpossibleActionException("Impossible pull action.")

        if (!isValidPosition(game, agentNewX, agentNewY)) throw ImpossibleActionException("Impossible pull action.")

        // move the agent
        game.level.update(agent, agentNewX, agentNewY)
        // move the box into the agents's old position
        game.level.update(box, agentX, agentY)

        return game
    }

    /**
     * Checks if it is valid to move into (x, y)
     * Returns false if (x, y) is not empty
     * Returns false if (x, y) is on or outside the edge of the map
     * Returns true otherwise
     */
    private fun isValidPosition(game: Pokoban, pair: Pair<Int, Int>) = isValidPosition(game, pair.first, pair.second)

    /**
     * Checks if it is valid to move into (x, y)
     * Returns false if (x, y) is not empty or a goal
     * Returns false if (x, y) is on or outside the edge of the map
     * Returns true otherwise
     */
    private fun isValidPosition(game: Pokoban, x: Int, y: Int): Boolean {
        return (game.level.height >= y && y >= 0)
                && (game.level.width >= x && x >= 0)
                && (game.level.get(x, y) == null)
                && (game.level.getWall(x, y) == null)
    }

    /**
     * Gets the relative position to (x, y), given direction
     */
    private fun getRelativePosition(pair: Pair<Int, Int>, direction: Direction): Pair<Int, Int> = getRelativePosition(pair.first, pair.second, direction)

    /**
     * Gets the relative position to (x, y), given direction
     */
    private fun getRelativePosition(x: Int, y: Int, direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair<Int, Int>(x, y - 1)
            Direction.SOUTH -> Pair<Int, Int>(x, y + 1)
            Direction.EAST -> Pair<Int, Int>(x + 1, y)
            Direction.WEST -> Pair<Int, Int>(x - 1, y)
        }
    }
}