package server.model

import server.model.objects.*
import server.services.LevelService

class Level(val filename: String,
            val mapfile: String,
            private val wallMap: MutableMap<Int, Wall>,
            private val goalMap: MutableMap<Int, Goal>,
            private val collisionMap: MutableMap<Int, PokobanObject>,
            val width: Int,
            val height: Int) {

    /**
     * Set the object at position (x, y), and removes it from current position
     * Assumes no illegal object collisions
     */
    fun update(pokobanObject: PokobanObject, x: Int, y: Int) {
        // remove object from it's current position
        collisionMap.remove(LevelService.instance.cantor(get(pokobanObject)))

        // add object to it's new position
        collisionMap.put(LevelService.instance.cantor(x, y), pokobanObject)
    }

    /**
     * Returns whether or not goal is solved
     */
    fun isSolved(goal: Goal): Boolean {
        val objectOnTopOfGoal = get(get(goal))
        return objectOnTopOfGoal != null
                && (objectOnTopOfGoal is Box && objectOnTopOfGoal.name.toLowerCase() == goal.name)
    }

    /**
     * Returns the wall at position (x, y)
     */
    fun getWall(x: Int, y: Int): PokobanObject? = wallMap[LevelService.instance.cantor(x, y)]

    /**
     * Returns the object at position (x, y) on the map
     */
    fun get(pair: Pair<Int, Int>): PokobanObject? = get(pair.first, pair.second)

    /**
     * Returns the object at position (x, y) on the map
     */
    fun get(x: Int, y: Int): PokobanObject? = collisionMap[LevelService.instance.cantor(x, y)]

    /**
     * Returns the position (x, y) of objectToFind on the map
     */
    fun get(objectToFind: PokobanObject): Pair<Int, Int> {
        val entry = when (objectToFind) {
            is Agent, is Box -> collisionMap.entries.filter { it.value == objectToFind }
            is Goal -> goalMap.entries.filter { it.value == objectToFind }
            is Wall -> wallMap.entries.filter { it.value == objectToFind }
            else -> throw RuntimeException("ObjectToFind must be an Agent, Box, Goal or Wall.")
        }

        if (entry.isEmpty()) {
            throw RuntimeException("Object does not exist")
        }
        if (entry.size > 1) throw RuntimeException("Multiple map entries exist for: " + objectToFind)
        return LevelService.instance.decantor(entry.first().key)
    }

    /**
     * Returns getPage agents
     */
    fun getAgents(): List<Agent> = collisionMap.values.filter({ it is Agent }).map { it as Agent }

    /**
     * Returns getPage boxes
     */
    fun getBoxes(): List<Box> = collisionMap.values.filter({ it is Box }).map { it as Box }

    /**
     * Returns getPage goals
     */
    fun getGoals(): List<Goal> = goalMap.values.map { it }

    /**
     * Returns getPage walls
     */
    fun getWalls(): List<Wall> = wallMap.values.map { it }

    /**
     * Returns a copy of the level
     */
    fun copy(): Level {
        return Level(filename, mapfile, HashMap(wallMap), HashMap(goalMap), HashMap(collisionMap), width, height)
    }
}

