package model

import com.google.gson.annotations.SerializedName

class Pokoban(val id: String, val level: Level) {

	/**
	 * Returns the current state of the game
	 */
	fun getState(): State {

		val agents: List<StateObject> = level.getAgents().map {
			val (row, col) = level.get(it)
			StateObject(row, col, it.name)
		}

		val boxes: List<StateObject> = level.getBoxes().map {
			val (row, col) = level.get(it)
			StateObject(row, col, it.name)
		}

		val goals: List<StateObject> = level.getGoals().map {
			val (row, col) = level.get(it)
			StateObject(row, col, it.name)
		}

		val walls: List<StateObject> = level.getWalls().map {
			val (row, col) = level.get(it)
			StateObject(row, col, it.name)
		}

		// assume width and height are the same
		return State(agents, boxes, goals, walls, level.width)
	}
}

data class State (
		@SerializedName("agents") val agents: List<StateObject>,
		@SerializedName("boxes") val boxes: List<StateObject>,
		@SerializedName("goals") val goals: List<StateObject>,
		@SerializedName("walls") val walls: List<StateObject>,
		@SerializedName("dimensions") val dimensions: Int
)

data class StateObject(
		@SerializedName("row") val row: Int,
		@SerializedName("col") val col: Int,
		@SerializedName("letter") val letter: String
)
