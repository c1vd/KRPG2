package entities

import items.clothes.armor.helmet.BronzeHelmet
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.openrndr.math.Vector2
import world.World


class PlayerTest {
    /*@Test
    fun equipTest() {
        val world = World("world1.world")
        val playerForTest = Player(Vector2(0.0, 0.0), 100.0, world)
        playerForTest.equip(BronzeHelmet)
        assert(playerForTest.helmet is BronzeHelmet)
        assert(playerForTest.takeOffHelmet() is BronzeHelmet)
        assert(playerForTest.helmet == null)
    }*/

    /*@Test
    fun healAndHitTest() {
        val world = World("world1.world")
        val playerForTest = Player(Vector2(0.0, 0.0), 100.0, world)
        playerForTest.heal(50.0)
        assertEquals(playerForTest.health, 150.0)
        playerForTest.heal(75.0)
        assertEquals(playerForTest.health, 200.0)
        playerForTest.hit(25.0)
        assertEquals(playerForTest.health, 175.0)
        playerForTest.hit(0.01)
        assertEquals(playerForTest.health, 174.0)
    }*/
}