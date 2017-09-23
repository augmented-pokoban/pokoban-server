package model.objects

class GoalBox(goal: Goal, val box: Box) : GoalObject(goal, box) {
	/**
	 * Returns true if given goal is solved
	 */
	fun isSolved(): Boolean = box.name.toLowerCase() == goal.name.toLowerCase()
}