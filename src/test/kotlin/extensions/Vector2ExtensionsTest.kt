package extensions

import org.junit.Test
import org.openrndr.math.Vector2

class Vector2ExtensionsTest {
    @Test
    fun roundTest(){
        val v1 = Vector2(1.23456, 2.314)
        println(v1.round(1))
    }
}