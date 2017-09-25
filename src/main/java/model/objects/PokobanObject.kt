package model.objects

import com.google.gson.annotations.SerializedName

abstract class PokobanObject(val id: String, val name: String) {

	override fun toString(): String = name

	override fun equals(other: Any?): Boolean = (other as PokobanObject).id == this.id

	override fun hashCode(): Int = id.hashCode()
}

data class PokobanObjectState(@SerializedName("col") val col: Int, // col = x
							  @SerializedName("row") val row: Int, // row = y
							  @SerializedName("letter") val letter: String) {
	constructor(pair: Pair<Int, Int>, letter: String) : this(pair.first, pair.second, letter)
}