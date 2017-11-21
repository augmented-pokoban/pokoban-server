package server.model

import com.google.gson.annotations.SerializedName
import server.model.objects.PokobanObjectState
import java.io.Serializable

class Pokoban(val id: String, val level: Level) : Serializable {

	/**
	 * Returns the current state of the game
	 */
	fun getState(): PokobanState {

		val agents: List<PokobanObjectState> = level.getAgents().map { PokobanObjectState(level.get(it), it.name) }
		val boxes: List<PokobanObjectState> = level.getBoxes().map { PokobanObjectState(level.get(it), it.name) }
		val goals: List<PokobanObjectState> = level.getGoals().map { PokobanObjectState(level.get(it), it.name) }
		val walls: List<PokobanObjectState> = level.getWalls().map { PokobanObjectState(level.get(it), it.name) }

		// assume width and height are the same
		return PokobanState(agents, boxes, goals, walls, level.width)
	}

	/**
	 * Returns true if all goals are solved
	 */
	fun isDone(): Boolean = level.getGoals().fold(true, { total, next -> total && level.isSolved(next) })

	/**
	 * Returns the number of solved goals
	 */
	fun numberOfSolvedGoals(): Int = level.getGoals().filter({ level.isSolved(it) }).size

	override fun equals(other: Any?): Boolean = super.equals(other)

	override fun hashCode(): Int = id.hashCode()
}

data class PokobanState(@SerializedName("agents") val agents: List<PokobanObjectState>,
                        @SerializedName("boxes") val boxes: List<PokobanObjectState>,
                        @SerializedName("goals") val goals: List<PokobanObjectState>,
                        @SerializedName("walls") val walls: List<PokobanObjectState>,
                        @SerializedName("dimensions") val dimensions: Int)

data class PokobanTransition(@SerializedName("reward") val reward: Number,
                             @SerializedName("success") val success: Boolean,
                             @SerializedName("done") val done: Boolean,
                             @SerializedName("action") val action: PokobanAction,
                             @SerializedName("state") val state: PokobanState)
