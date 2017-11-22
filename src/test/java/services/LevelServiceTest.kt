package services

import org.junit.Ignore
import org.junit.Test
import server.services.LevelService
import kotlin.test.assertEquals

class LevelServiceTest {

    @Test
    fun testCantor() {

        assertEquals(Pair(1,1), LevelService.instance.decantor(
                LevelService.instance.cantor(1, 1)
        ))
        assertEquals(Pair(1,2), LevelService.instance.decantor(
                LevelService.instance.cantor(1, 2)
        ))
        assertEquals(Pair(2,1), LevelService.instance.decantor(
                LevelService.instance.cantor(2, 1)
        ))
        assertEquals(Pair(2,2), LevelService.instance.decantor(
                LevelService.instance.cantor(2, 2)
        ))
    }

    @Ignore("file no longer exists")
    @Test
    fun testLoadLevel() {

//        val level = LevelService.instance.loadLevel("easy_1_box_1.lvl")

//        assertEquals("0", level.get(1, 1).toString())
    }
}