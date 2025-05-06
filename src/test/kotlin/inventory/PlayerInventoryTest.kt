package inventory

import items.clothes.armor.helmet.BronzeHelmet
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PlayerInventoryTest {
    @Test
    fun addTest() {
        val inv = PlayerInventory()
        repeat(11) {inv.add(BronzeHelmet)}

        assertEquals(10, inv.items.size)
        assertEquals(false, inv.add(BronzeHelmet))
    }
}