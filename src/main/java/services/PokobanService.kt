package services

import exceptions.ImpossibleActionException
import model.Direction
import model.Pokoban
import model.PokobanAction
import model.objects.Agent
import model.objects.Goal
import java.util.*
import kotlin.collections.HashMap

/**
 * Singleton PokobanService which holds all current games in-memory
 */
class PokobanService private constructor() {
	private object Holder {
		val INSTANCE = PokobanService()
	}

	companion object {
		val instance: PokobanService by lazy { Holder.INSTANCE }
	}

	// Games currently being played
	private val games: MutableMap<String, Pokoban> = HashMap()

	/**
	 * Start a new game
	 */
	fun start(filename: String): Pokoban {
		val level = LevelService.instance.loadLevel(filename)
		val gameId = UUID.randomUUID().toString()
		val newGame = Pokoban(gameId, level);

		instance.games.put(gameId, newGame);

		return newGame;
	}

	/**
	 * Returns given game
	 */
	fun get(id: String): Pokoban = instance.games.getValue(id);

	/**
	 * Returns all games
	 */
	fun all(): List<Pokoban> = instance.games.values.toList()

	/**
	 * Transitions given game into a new state
	 * Returns (reward, game)
	 */
	fun transition(gameId: String, action: PokobanAction): Pair<Int, Pokoban> {

		var game = instance.get(gameId)
		val agent: Agent = game.level.getAgents().first() // We assume only 1 agent
		val solvedGoalsBefore = game.numberOfSolvedGoals()

		game = when (action) { // kotlin is awesome
			PokobanAction.MOVE_NORTH -> move(game, agent, Direction.NORTH)
			PokobanAction.MOVE_SOUTH -> move(game, agent, Direction.SOUTH)
			PokobanAction.MOVE_EAST -> move(game, agent, Direction.EAST)
			PokobanAction.MOVE_WEST -> move(game, agent, Direction.WEST)
			PokobanAction.PUSH_NORTH -> push(game, agent, Direction.NORTH)
			PokobanAction.PUSH_SOUTH -> push(game, agent, Direction.SOUTH)
			PokobanAction.PUSH_EAST -> push(game, agent, Direction.EAST)
			PokobanAction.PUSH_WEST -> push(game, agent, Direction.WEST)
			PokobanAction.PULL_NORTH -> pull(game, agent, Direction.NORTH)
			PokobanAction.PULL_SOUTH -> pull(game, agent, Direction.SOUTH)
			PokobanAction.PULL_EAST -> pull(game, agent, Direction.EAST)
			PokobanAction.PULL_WEST -> pull(game, agent, Direction.WEST)
		}

		// update the game in singleton instance
		instance.update(gameId, game)

		// calculate reward
		val solvedGoalsAfter = game.numberOfSolvedGoals()
		val reward: Int = when {
			solvedGoalsBefore > solvedGoalsAfter -> -10 // we suck!
			solvedGoalsBefore < solvedGoalsAfter -> 10 // we solved a goal!
			else -> -1
		}

		return Pair(reward, game)
	}

	/**
	 * Updates given game in hashmap
	 */
	private fun update(id: String, game: Pokoban) = instance.games.put(id, game)

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
		val box = game.level.get(boxX, boxY) ?: return game
		val (boxNewX, boxNewY) = getRelativePosition(boxX, boxY, direction)

		if (isValidPosition(game, boxNewX, boxNewY)) throw ImpossibleActionException("Impossible push action.")

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
		val box = game.level.get(boxX, boxY) ?: return game

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
		return !((game.level.height <= y || y == 0)
				|| (game.level.width <= x || x == 0)
				|| (game.level.get(x, y) != null && game.level.get(x, y) !is Goal))
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