package server.services

import server.model.Level
import server.model.objects.*
import java.io.File
import java.util.*

/**
 * Kotlin is awesome!
 * Here we add a .contains method to the Regex class!
 */
operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

class LevelService private constructor() {

	private object Holder {
		val INSTANCE = LevelService()
	}

	companion object {
		val instance: LevelService by lazy { Holder.INSTANCE }
	}

	fun loadLevel(filePath: String): Level {

		val wallMap: MutableMap<Int, Wall> = HashMap()
		val goalMap: MutableMap<Int, Goal> = HashMap()
		val collisionMap: MutableMap<Int, PokobanObject> = HashMap()

		var width: Int = 0
		var height: Int = 0

		var mapfile = ""

		// iterate over lines in level file
		File(filePath).readLines().forEachIndexed { y, line ->
			{
				mapfile += line + "\n"
				if (y > height) height = y
				// iterate over each character in line

				width = maxOf(line.replace("\n", "").length, width)

				line.split(Regex("")).forEachIndexed { x, field ->
					{
						val fieldId = UUID.randomUUID().toString()
						val coordinate = cantor(x, y)

						// determine object at position in file
						when (field) {
							"+" -> wallMap.put(coordinate, Wall(fieldId, field))
							in Regex("^[a-z]$") -> goalMap.put(coordinate, Goal(fieldId, field))
							in Regex("^[A-Z]$") -> collisionMap.put(coordinate, Box(fieldId, field))
							in Regex("^[0-9]$") -> collisionMap.put(coordinate, Agent(fieldId, field))
							else -> {
								// who cares?
							}
						}
					}() // executes this block
				}
			}() // executes this block
		}

		return Level(File(filePath).name, mapfile, wallMap, goalMap, collisionMap, width, height + 1)
	}

	/**
	 * Helper function for pair instances
	 */
	fun cantor(pair: Pair<Int, Int>): Int = cantor(pair.first, pair.second)

	/**
	 * Cantor pairing function implementation
	 * [https://en.wikipedia.org/wiki/Pairing_function#Cantor_pairing_function]
	 */
	fun cantor(key1: Int, key2: Int): Int = (((key1 + key2) * (key1 + key2 + 1)) / 2) + key2

	/**
	 * Returns the two integers comprised of the cantor pairing {@param key}
	 */
	fun decantor(key: Int): Pair<Int, Int> {
		// how to math even?
		val t = Math.floor((-1.0 + Math.sqrt(1.0 + 8 * key)) / 2.0).toInt()
		val x = t * (t + 3) / 2 - key
		val y = key - t * (t + 1) / 2
		return Pair(x, y)
	}
}