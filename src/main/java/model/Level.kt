package model

import services.LevelService

class Level(val mapfile: String,
			private val map: MutableMap<Int, LevelObject>,
			val width: Int,
			val height: Int) {

	/**
	 * Returns the object at position (x, y) on the map
	 */
	fun get(x: Int, y: Int): LevelObject = map.getValue(LevelService.instance.cantor(x, y))

	/**
	 * Returns the object at position (x, y) on the map
	 */
	fun get(objectToFind: LevelObject): Pair<Int, Int> {
		val entry = map.entries.filter { it.value === objectToFind }
		if (entry.size > 1) throw RuntimeException("Multiple map entries exist for: " + objectToFind)
		return LevelService.instance.decantor(entry.first().key)
	}

	fun getAgents(): List<LevelObject> = map.values.filter { it is Agent }

	fun getBoxes(): List<LevelObject> = map.values.filter { it is Box }

	fun getGoals(): List<LevelObject> = map.values.filter { it is Goal }

	fun getWalls(): List<LevelObject> = map.values.filter { it is Wall }
}

