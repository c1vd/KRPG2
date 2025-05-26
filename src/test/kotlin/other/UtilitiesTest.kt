package other

import engine.other.checkIndex
import engine.other.clampProgression
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UtilitiesTest {
    @Test
    fun testCheckIndex() {
        assertTrue(checkIndex(1, 10))
        assertTrue(checkIndex(1, 2))
        assertTrue(checkIndex(9, 10))
        assertTrue(checkIndex(0, 1))
        assertFalse(checkIndex(9, 9))
        assertFalse(checkIndex(9, 8))
        assertFalse(checkIndex(1, 1))
    }

    @Test
    fun testClampProgression() {
        assertEquals(5..10, clampProgression(1, 10, 5, 11))
        assertEquals(5..6, clampProgression(1, 10, 5, 6))
    }

    @Test
    fun testInBlock(){
        
    }
}