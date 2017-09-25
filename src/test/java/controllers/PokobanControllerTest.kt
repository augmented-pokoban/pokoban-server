package controllers

import com.mashape.unirest.http.Unirest
import org.json.JSONObject
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

	@Ignore("Requires running server")
	@Test
	fun testMove() {
		// start a new game
		var response = Unirest.post("http://localhost:8080/pokoban-server/test").asJson().body.`object`

		val gameId = response.get("gameID")

		// move agent east (2, 1)
		response = Unirest.post("http://localhost:8080/pokoban-server/$gameId/move-east").asJson().body.`object`
		assertEquals(1, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("row"))
		assertEquals(2, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("col"))

		// move agent south (2, 2)
		response = Unirest.post("http://localhost:8080/pokoban-server/$gameId/move-south").asJson().body.`object`
		assertEquals(2, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("row"))
		assertEquals(2, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("col"))

		// move agent west (1, 2)
		response = Unirest.post("http://localhost:8080/pokoban-server/$gameId/move-west").asJson().body.`object`
		assertEquals(2, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("row"))
		assertEquals(1, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("col"))

		// move agent north (1, 1)
		response = Unirest.post("http://localhost:8080/pokoban-server/$gameId/move-north").asJson().body.`object`
		assertEquals(1, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("row"))
		assertEquals(1, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("col"))
	}

	@Ignore("Requires running server")
	@Test
	fun testPush() {
		// start a new game
		var response = Unirest.post("http://localhost:8080/pokoban-server/test2").asJson().body.`object`

		val gameId = response.get("gameID")

		// push box east (15, 1) -> (16, 1)
		response = Unirest.post("http://localhost:8080/pokoban-server/$gameId/push-east").asJson().body.`object`

		assertEquals(1, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("row"))
		assertEquals(15, (response.getJSONObject("state").getJSONArray("agents").first() as JSONObject).get("col"))

		assertEquals(1, (response.getJSONObject("state").getJSONArray("boxes").first() as JSONObject).get("row"))
		assertEquals(16, (response.getJSONObject("state").getJSONArray("boxes").first() as JSONObject).get("col"))

		assertEquals(-1, (response.get("reward")))
	}
}