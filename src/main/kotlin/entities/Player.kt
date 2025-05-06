package entities

import blocks.DefaultBlock
import blocks.UnbreakableBlock
import inventory.PlayerInventory
import items.clothes.armor.Breastplate
import items.Equipment
import items.Item
import items.clothes.armor.Helmet
import items.clothes.armor.Leggings
import math.clamp
import math.inBlock
import math.worldSizeX
import math.worldSizeY
import org.openrndr.math.Vector2
import world.World
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

/* класс игрока, не является зависимым от камеры */

class Player(
    override var x: Double, override var y: Double, override var health: Double, private val world: World,
    override var maxHealth: Double = 100.0, var speed: Double = 0.1
) : Entity {
    var helmet: Helmet? = null
    var breastplate: Breastplate? = null
    var leggings: Leggings? = null
    val inventory: PlayerInventory = PlayerInventory()

    /* методы класса Player, которые нужны для управления снаряжением игрока */

    fun equip(item: Equipment): Equipment? {
        when (item) {
            is Helmet -> {
                return helmet.also { helmet = item }
            }

            is Breastplate -> {
                return breastplate.also { breastplate = item }
            }

            is Leggings -> {
                return leggings.also { leggings = item }
            }

            else -> {
                println("SORRY")
            }
        }
        return null
    }

    fun takeOffHelmet(): Helmet? {
        // данная реализация алгоритма снятия шлема была сделана с целью упрощения чтения кода
        // возвращается шлем, а потом у игрока он становится равным null
        return helmet.also { helmet = null }
    }

    fun takeOffBreastplate(): Breastplate? {
        return breastplate.also { breastplate = null }
    }

    fun takeOffLeggings(): Leggings? {
        return leggings.also { leggings = null }
    }

    fun getSumOfProtection(): Double {
        return (helmet?.protection ?: 0.0) + (breastplate?.protection ?: 0.0) + (leggings?.protection ?: 0.0)
    }

    /* методы класса Player, отвечающие за HP */

    /**
     * метод, который наносит урон игроку
     *
     * @param damage урон, который нужно нанести игроку
     */
    fun hit(damage: Double) {
        // в случае атаки на игрока, игрок потеряет как минимум 1 hp
        health -= max(damage - getSumOfProtection(), 1.0)
    }

    fun heal(healPoints: Double) {
        health = min(health + healPoints, maxHealth)
    }

    /* методы класса Player, которые отвечают за управление инвентарём */

    fun addItem(item: Item) {
        inventory.add(item)
    }

    fun destroy(block: DefaultBlock): Item? {
        if (block is UnbreakableBlock) {
            return null
        }
        return block.blockItem
    }

    /* методы, отвечающие за движение */

    private fun checkPosition(position: Vector2): Boolean {
        for (blockX in clamp((position.x - 2).toInt(), 0, worldSizeX - 1)..clamp(
            (position.x + 2).toInt(),
            0,
            worldSizeX - 1
        )) {
            for (blockY in clamp((position.y - 2).toInt(), 0, worldSizeY - 1)..clamp(
                (position.y + 2).toInt(),
                0,
                worldSizeY - 1
            )) {

                if (world.blockList[blockY][blockX] != null) {
                    val blockPosition = Vector2(blockX.toDouble(), blockY.toDouble())
                    if (inBlock(position, blockPosition)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun goTo(position: Vector2) {
        if (checkPosition(position)) {
            x = position.x
            y = position.y
        }
    }

    fun right() {
        goTo(Vector2(x + speed, y))
    }

    fun left() {
        goTo(Vector2(x - speed, y))
    }

    fun up() {
        goTo(Vector2(x, y - speed))
    }

    fun down() {
        goTo(Vector2(x, y + speed))
    }
}