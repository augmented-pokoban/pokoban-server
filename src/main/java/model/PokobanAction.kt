package model

enum class PokobanAction(action: String) {

	MOVE_NORTH("move-north"),
	MOVE_SOUTH("move-south"),
	MOVE_EAST("move-east"),
	MOVE_WEST("move-west"),
	PULL_NORTH("pull-north"),
	PULL_SOUTH("pull-south"),
	PULL_EAST("pull-east"),
	PULL_WEST("pull-west"),
	PUSH_NORTH("push-north"),
	PUSH_SOUTH("push-south"),
	PUSH_EAST("push-east"),
	PUSH_WEST("push-west")
}