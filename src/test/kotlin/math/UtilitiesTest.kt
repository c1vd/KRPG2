package math

import org.junit.Test
import kotlin.test.assertEquals

class UtilitiesTest {
    @Test
    fun clampTest(){
        assertEquals(clamp(10, 20, 50), 20)
        assertEquals(clamp(30, 20, 50), 30)
    }
}