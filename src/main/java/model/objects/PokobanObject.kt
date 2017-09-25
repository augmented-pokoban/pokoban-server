package model.objects

import com.google.gson.annotations.SerializedName

abstract class PokobanObject(val id: String, val name: String) {

	override fun equals(other: Any?): Boolean {
		return (other as PokobanObject).id == this.id ||
				other.id.split(":").first() == this.id ||
				other.id.split(":").last() == this.id||
				other.id == this.id.split(":").first() ||
				other.id == this.id.split(":").last()
	}

	override fun toString(): String = name
}

data class PokobanObjectState(@SerializedName("col") val col: Int, // col = x
							  @SerializedName("row") val row: Int, // row = y
							  @SerializedName("letter") val letter: String) {
	constructor(pair: Pair<Int, Int>, letter: String) : this(pair.first, pair.second, letter)
}