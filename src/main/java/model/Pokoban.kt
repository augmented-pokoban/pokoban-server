package model

import com.google.gson.annotations.SerializedName
import model.objects.PokobanObjectState

class Pokoban(val id: String, val level: Level) {

	/**
	 * Returns the current state of the game
	 */
	fun getState(): PokobanState {

		val agents: List<PokobanObjectState> = level.getAgents().map {
			val (row, col) = level.get(it)
			PokobanObjectState(row, col, it.name)
		}

		val boxes: List<PokobanObjectState> = level.getBoxes().map {
			val (row, col) = level.get(it)
			PokobanObjectState(row, col, it.name)
		}

		val goals: List<PokobanObjectState> = level.getGoals().map {
			val (row, col) = level.get(it)
			PokobanObjectState(row, col, it.name)
		}

		val walls: List<PokobanObjectState> = level.getWalls().map {
			val (row, col) = level.get(it)
			PokobanObjectState(row, col, it.name)
		}

		// assume width and height are the same
		return PokobanState(agents, boxes, goals, walls, level.width)
	}
}

data class PokobanState(
		@SerializedName("agents") val agents: List<PokobanObjectState>,
		@SerializedName("boxes") val boxes: List<PokobanObjectState>,
		@SerializedName("goals") val goals: List<PokobanObjectState>,
		@SerializedName("walls") val walls: List<PokobanObjectState>,
		@SerializedName("dimensions") val dimensions: Int
)