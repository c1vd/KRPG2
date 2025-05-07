package entities

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


    /**
     * метод, с помощью которого можно надеть на игрока экипировку
     *
     * @param item предмет, который нужно надеть
     *
     * @return экипировка, на место которой надели новую, либо null, если ничего снимать не надо
     */
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

    // методы, ответственные за снятие экипировки
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

    // получение сумму защиты всей экипировки, надетой на игрока
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

    fun throwItem(index: Int): Item? {
        return inventory.get(index)
    }

    /* методы, отвечающие за движение */

    private fun checkPosition(position: Vector2): Boolean {
        val xRange = clamp((position.x - 2).toInt(), 0, worldSizeX - 1)..clamp(
            (position.x + 2).toInt(),
            0,
            worldSizeX - 1
        )
        val yRange = clamp((position.y - 2).toInt(), 0, worldSizeY - 1)..clamp(
            (position.y + 2).toInt(),
            0,
            worldSizeY - 1
        )
        for (blockX in xRange) {
            for (blockY in yRange) {
                if (world.getBlock(blockX, blockY) != null) {
                    val blockPosition = Vector2(blockX.toDouble(), blockY.toDouble())
                    if (inBlock(position, blockPosition)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun goTo(position: Vector2) {
        if (checkPosition(position)) {
            x = position.x
            y = position.y
        }
    }
    fun go(position: Vector2, frametime: Double){
        val div = position - Vector2(x, y)
        goTo((div/div.length)*frametime*speed + Vector2(x, y))
    }
}