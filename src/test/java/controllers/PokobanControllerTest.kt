package controllers

import com.mashape.unirest.http.Unirest
import org.junit.Test

class PokobanControllerTest {

	@Test
	fun testCreate() {

		val response = Unirest.post("http://localhost:8080/pokoban-server")

		println(response)
	}
}