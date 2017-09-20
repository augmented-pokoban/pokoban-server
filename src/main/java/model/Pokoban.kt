package model

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

data class State(val agents: List<StateObject>,
				 val boxes: List<StateObject>,
				 val goals: List<StateObject>,
				 val walls: List<StateObject>,
				 val dimensions: Int)

data class StateObject(val row: Int, val col: Int, val letter: String)
