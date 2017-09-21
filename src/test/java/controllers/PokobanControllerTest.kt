package controllers

import com.mashape.unirest.http.Unirest
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals

class PokobanControllerTest {

	@Ignore("Requires running server")
	@Test
	fun testIndex() {
		val response = Unirest.get("http://localhost:8080/pokoban-server/").asJson().body.array
		assertEquals(0, response.length())
	}

	@Ignore("Requires running server")
	@Test
	fun testCreate() {
		val response1 = Unirest.post("http://localhost:8080/pokoban-server/test").asJson().body.`object`

		val gameId = response1.get("gameID")

		val response2 = Unirest.get("http://localhost:8080/pokoban-server/" + gameId).asJson().body.`object`

		val response3 = Unirest.get("http://localhost:8080/pokoban-server").asJson().body.`array`

		assertEquals(1, response3.length())
	}
}