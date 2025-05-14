package extensions

import org.junit.Test
import org.openrndr.math.Vector2
import kotlin.test.assertEquals

class Vector2ExtensionsTest {
    @Test
    fun roundTest(){
        val v1 = Vector2(1.23456, 2.314)
        assertEquals(Vector2(1.2, 2.3), v1.round(1))
    }
}