package services

import model.Pokoban
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
}