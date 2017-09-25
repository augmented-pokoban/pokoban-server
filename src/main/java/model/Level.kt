package model

import model.objects.*
import services.LevelService

class Level(val mapfile: String,
			private val immovableMap: MutableMap<Int, PokobanObject>,
			private val moveableMap: MutableMap<Int, PokobanObject>,
			val width: Int,
			val height: Int) {

	/**
	 * Set the object at position (x, y), and removes it from current position
	 * Assumes no illegal object collisions
	 */
	fun update(pokobanObject: PokobanObject, x: Int, y: Int) {
		// remove object from it's current position
		moveableMap.remove(LevelService.instance.cantor(get(pokobanObject)))

		// add object to it's new position
		moveableMap.put(LevelService.instance.cantor(x, y), pokobanObject)
	}

	/**
	 * Returns whether or not goal is solved
	 */
	fun isSolved(goal: Goal): Boolean {
		val objectAtGoalPosition = get(get(goal))
		return objectAtGoalPosition != null
				&& (objectAtGoalPosition is Box && objectAtGoalPosition.name.toLowerCase() == goal.name)
	}

	/**
	 * Returns the object at position (x, y) on the map
	 */
	fun get(pair: Pair<Int, Int>): PokobanObject? = get(pair.first, pair.second)

	/**
	 * Returns the object at position (x, y) on the map
	 */
	fun get(x: Int, y: Int): PokobanObject? = moveableMap[LevelService.instance.cantor(x, y)]

	/**
	 * Returns the position (x, y) of objectToFind on the map
	 */
	fun get(objectToFind: PokobanObject): Pair<Int, Int> {
		val entry = (moveableMap.entries + immovableMap.entries).filter { it.value == objectToFind }
		if (entry.isEmpty()) throw RuntimeException("Object does not exist")
		if (entry.size > 1) throw RuntimeException("Multiple map entries exist for: " + objectToFind)
		return LevelService.instance.decantor(entry.first().key)
	}

	/**
	 * Returns all agents
	 */
	fun getAgents(): List<Agent> = moveableMap.values.filter({ it is Agent }).map { it as Agent }

	/**
	 * Returns all boxes
	 */
	fun getBoxes(): List<Box> = moveableMap.values.filter({ it is Box }).map { it as Box }

	/**
	 * Returns all goals
	 */
	fun getGoals(): List<Goal> = immovableMap.values.filter({ it is Goal }).map { it as Goal }

	/**
	 * Returns all walls
	 */
	fun getWalls(): List<Wall> = immovableMap.values.filter({ it is Wall }).map { it as Wall }
}

