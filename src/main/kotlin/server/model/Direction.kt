package server.model

enum class Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;

	/**
	 * Returns the inverse of a given position
	 */
	fun inverse(): Direction {
		return when (this) {
			NORTH -> SOUTH
			SOUTH -> NORTH
			EAST -> WEST
			WEST -> EAST
		}
	}
}