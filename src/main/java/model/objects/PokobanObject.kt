package model.objects

import com.google.gson.annotations.SerializedName

abstract class PokobanObject(val id: String, val name: String) {

    override fun hashCode(): Int = id.toInt()

    override fun equals(other: Any?): Boolean = super.equals(other)

    override fun toString(): String = name
}

data class PokobanObjectState(
		@SerializedName("row") val row: Int,
		@SerializedName("col") val col: Int,
		@SerializedName("letter") val letter: String
)