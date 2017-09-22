package model

import model.objects.*
import services.LevelService

class Level(val mapfile: String,
			private val map: MutableMap<Int, PokobanObject>,
			val width: Int,
			val height: Int) {

	/**
	 * Set the object at position (x, y), and removes it from current position
	 * Assumes no object collisions
	 */
	fun update(pokobanObject: PokobanObject, x: Int, y: Int) {
		// remove object from it's current position
		map.remove(LevelService.instance.cantor(get(pokobanObject)))
		map.put(LevelService.instance.cantor(x, y), pokobanObject)
	}

	/**
	 * Returns the object at position (x, y) on the map
	 */
	fun get(pair: Pair<Int, Int>): PokobanObject? = get(pair.first, pair.second)

	/**
	 * Returns the object at position (x, y) on the map
	 */
	fun get(x: Int, y: Int): PokobanObject? = map[LevelService.instance.cantor(x, y)]

	/**
	 * Returns the position (x, y) of objectToFind on the map
	 */
	fun get(objectToFind: PokobanObject): Pair<Int, Int> {
		val entry = map.entries.filter { it.value === objectToFind }
		if (entry.size > 1) throw RuntimeException("Multiple map entries exist for: " + objectToFind)
		if (entry.isEmpty()) throw RuntimeException("Object does not exist")
		return LevelService.instance.decantor(entry.first().key)
	}

	fun getAgents(): List<Agent> = (map.values.filter { it is Agent }).map { it as Agent }

	fun getBoxes(): List<Box> = (map.values.filter({ it is Box })).map { it as Box }

	fun getGoals(): List<Goal> = (map.values.filter { it is Goal }).map { it as Goal }

	fun getWalls(): List<Wall> = (map.values.filter { it is Wall }).map { it as Wall }
}

