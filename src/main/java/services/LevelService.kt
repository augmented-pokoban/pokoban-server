package services

import model.*
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

	fun loadLevel(filename: String): Level {

		val map: MutableMap<Int, LevelObject> = HashMap()

		var width: Int = 0
		var height: Int = 0

		var mapfile = ""

		// iterate over lines in level file
		File(javaClass.classLoader.getResource("levels/" + filename).toURI()).readLines().forEachIndexed { y, line ->
			{
				mapfile += line
				if (y > height) height = y
				// iterate over each character in line
				line.split(Regex("")).forEachIndexed { x, field ->
					{
						if (x > width) width = x

						val fieldId = UUID.randomUUID().toString()
						val coordinate = cantor(x, y)

						// determine object at position in file
						when (field) {
							"+" -> map.put(coordinate, Wall(fieldId, field))
							in Regex("^[0-9]$") -> map.put(coordinate, Agent(fieldId, field))
							in Regex("^[a-z]$") -> map.put(coordinate, Box(fieldId, field))
							in Regex("^[A-Z]$") -> map.put(coordinate, Goal(fieldId, field))
							else -> {
								// who cares?
							}
						}
					}() // executes this block
				}
			}() // executes this block
		}

		return Level(mapfile, map, width, height)
	}

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