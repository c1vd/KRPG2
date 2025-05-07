package math

import org.junit.Test
import kotlin.test.assertEquals

class UtilitiesTest {
    @Test
    fun clampTest(){
        assertEquals(20, clamp(10, 20, 50))
        assertEquals(30, clamp(30, 20, 50))
    }
}