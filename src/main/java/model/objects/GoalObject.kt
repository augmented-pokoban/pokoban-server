package model.objects

abstract class GoalObject(val goal: Goal, pokobanObject: PokobanObject) : PokobanObject("${goal.id}:${pokobanObject.id}", "${goal.name}:${pokobanObject.name}")