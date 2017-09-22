package services

import model.Direction
import model.Pokoban
import model.PokobanAction
import model.objects.Agent
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
	fun get(id: String): Pokoban {
		return instance.games.getValue(id);
	}

	/**
	 * Returns all games
	 */
	fun all(): List<Pokoban> {
		return instance.games.values.toList()
	}

	/**
	 * Transitions given game into a new state
	 */
	fun transition(gameId: String, action: PokobanAction): Pokoban {

		val game = instance.get(gameId)
		val agent: Agent = game.level.getAgents().first() // We assume only 1 agent

		when (action) {
			PokobanAction.MOVE_NORTH -> move(game, agent, Direction.NORTH)
			PokobanAction.MOVE_SOUTH -> move(game, agent, Direction.SOUTH)
			PokobanAction.MOVE_EAST -> move(game, agent, Direction.EAST)
			PokobanAction.MOVE_WEST -> move(game, agent, Direction.WEST)
			else -> {
				// well shit
			}
		}

		return game
	}

	/**
	 * Moves the agent 1 step in given direction - if possible
	 * Returns the game with an updated state
	 */
	private fun move(game: Pokoban, agent: Agent, direction: Direction): Pokoban {

		val (currentX, currentY) = game.level.get(agent)
		var newX: Int = currentX
		var newY: Int = currentY

		when (direction) {
			Direction.NORTH -> newY++
			Direction.SOUTH -> newY--
			Direction.EAST -> newX++
			Direction.WEST -> newX--
		}

		// Is the new position even valid?
		if (game.level.height <= newY || newY == 0) {
			// New position is on the border of the top or bottom edge of the level, or outside the level
			// We simply return the same game instance, since this action is invalid
			return game
		}
		if (game.level.width <= newX || newX == 0) {
			// New position is on the border of the left or right edge of the level, or outside the level
			// We simply return the same game instance, since this action is invalid
			return game
		}

		val newPosition = game.level.get(newX, newY)
		if (newPosition != null) {
			// Something is already located at this position!
			// We simply return the same game instance, since this action is invalid
			return game
		}

		// Perform the move
		game.level.update(agent, newX, newY)

		return game
	}

	private fun push(game: Pokoban, agent: Agent, direction: Direction) {

	}

	private fun pull(game: Pokoban, agent: Agent, direction: Direction) {

	}
}