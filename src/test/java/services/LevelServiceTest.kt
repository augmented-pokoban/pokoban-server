package services

import org.junit.Test
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

    @Test
    fun testLoadLevel() {

        val level = LevelService.instance.loadLevel("test.lvl")

        assertEquals("0", level.get(1, 1).toString())
    }
}