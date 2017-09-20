package model

abstract class LevelObject(val id: String, val name: String) {

    override fun hashCode(): Int = id.toInt()

    override fun equals(other: Any?): Boolean = super.equals(other)

    override fun toString(): String = name
}