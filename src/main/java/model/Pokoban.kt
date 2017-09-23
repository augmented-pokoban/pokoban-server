package model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import model.objects.PokobanObjectState
import java.io.Serializable

class Pokoban(val id: String, val level: Level) : Serializable {

	private val gson = Gson()

	/**
	 * Returns the current state of the game
	 */
	fun getState(): PokobanState {

		val agents: List<PokobanObjectState> = level.getAgents().map { PokobanObjectState(level.get(it), it.name) }
		val boxes: List<PokobanObjectState> = level.getBoxes().map { PokobanObjectState(level.get(it), it.name) }
		val goals: List<PokobanObjectState> = level.getGoals().map { PokobanObjectState(level.get(it), it.name) }
		val goalBoxes: List<PokobanObjectState> = level.getGoalBoxess().map { PokobanObjectState(level.get(it), it.name) }
		val goalAgents: List<PokobanObjectState> = level.getGoalAgents().map { PokobanObjectState(level.get(it), it.name) }
		val walls: List<PokobanObjectState> = level.getWalls().map { PokobanObjectState(level.get(it), it.name) }

		// assume width and height are the same
		return PokobanState(agents, boxes, goals, goalBoxes, goalAgents, walls, level.width)
	}

	/**
	 * Returns true if all goals are solved
	 */
	fun isDone(): Boolean = (level.getGoalBoxess().map { it.isSolved() }).reduce { acc, isSolved -> acc && isSolved }

	override fun equals(other: Any?): Boolean = super.equals(other) || gson.toJson(getState()) == gson.toJson(other)

	override fun hashCode(): Int = id.hashCode()
}

data class PokobanState(@SerializedName("agents") val agents: List<PokobanObjectState>,
						@SerializedName("boxes") val boxes: List<PokobanObjectState>,
						@SerializedName("goals") val goals: List<PokobanObjectState>,
						@SerializedName("goalBoxes") val goalBoxes: List<PokobanObjectState>,
						@SerializedName("goalAgents") val goalAgents: List<PokobanObjectState>,
						@SerializedName("walls") val walls: List<PokobanObjectState>,
						@SerializedName("dimensions") val dimensions: Int)