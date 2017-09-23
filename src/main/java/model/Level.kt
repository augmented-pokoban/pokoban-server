package model

import model.objects.*
import services.LevelService

class Level(val mapfile: String,
			private val map: MutableMap<Int, PokobanObject>,
			val width: Int,
			val height: Int) {

	/**
	 * Set the object at position (x, y), and removes it from current position
	 * Assumes no illegal object collisions
	 * Returns the reward for performing the given action
	 */
	fun update(pokobanObject: PokobanObject, x: Int, y: Int): Number {
		val objectAtPosition = get(x, y)
		var objectToPut: PokobanObject = pokobanObject

		// check if we are putting something on a goal
		if (objectAtPosition is Goal) {
			objectToPut = when (pokobanObject) {
				is Box -> GoalBox(objectAtPosition, pokobanObject) // move box into goal
				is Agent -> GoalAgent(objectAtPosition, pokobanObject) // move agent into goal
				is GoalBox -> GoalBox(objectAtPosition, pokobanObject.box) // move box from one goal to another
				is GoalAgent -> GoalAgent(objectAtPosition, pokobanObject.agent) // move agent from one goal to another
				else -> throw RuntimeException("Are you trying to move a wall?")
			}
		}

		// check if we need to split a GoalBox or GoalAgent
		when (pokobanObject) {
			is GoalBox,
			is GoalAgent -> {
				// add the empty goal back at current position
				map.put(LevelService.instance.cantor(get(pokobanObject)), (pokobanObject as GoalObject).goal)
			}
			else -> {
				// remove object from it's current position
				map.remove(LevelService.instance.cantor(get(pokobanObject)))
			}
		}

		// add object to it's new position
		map.put(LevelService.instance.cantor(x, y), objectToPut)

		// calculate reward
		return when (objectToPut) {
			is GoalBox -> if (objectToPut.isSolved()) 10 else -1
			else -> -1
		}
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

	fun getGoalBoxess(): List<GoalBox> = (map.values.filter { it is GoalBox }).map { it as GoalBox }

	fun getGoalAgents(): List<GoalAgent> = (map.values.filter { it is GoalAgent }).map { it as GoalAgent }

	fun getWalls(): List<Wall> = (map.values.filter { it is Wall }).map { it as Wall }
}

