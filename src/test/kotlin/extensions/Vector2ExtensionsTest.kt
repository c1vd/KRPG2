package extensions

import org.junit.Test
import org.openrndr.math.Vector2
import org.openrndr.panel.elements.round
import kotlin.test.assertEquals

class Vector2ExtensionsTest {
    val roundDecimals = 5
    @Test
    fun roundTest(){
        val v1 = Vector2(1.23456, 2.314)
        assertEquals(Vector2(1.2, 2.3), v1.round(1))
    }

    @Test
    fun testUnit(){
        assertEquals(1.0, Vector2(0.1, 0.1).unit().length.round(roundDecimals))
    }

    @Test
    fun testXVector2(){
        assertEquals(Vector2(5.0, 0.0), Vector2(5.0, 2.0).xVector2())
    }
}