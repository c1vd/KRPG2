package entities

import extensions.round
import extensions.unit
import extensions.xVector2
import extensions.yVector2
import other.clampProgression
import other.inBlock
import org.openrndr.math.Vector2
import scene.Scene


class Player(
    override var scene: Scene,
    override var position: Vector2 = Vector2(0.0, 0.0),
    override val sizeVector: Vector2 = Vector2(1.0, 1.0),
    private var speed: Double = 5.0,
    override val name: String = "Player"
) : Entity {



    private fun isPlayerInBlock(position: Vector2, blockPosition: Vector2): Boolean {
        return inBlock(position, blockPosition) ||
                inBlock(position + sizeVector, blockPosition) ||
                inBlock(position + sizeVector.xVector2(), blockPosition) ||
                inBlock(position + sizeVector.yVector2(), blockPosition)
    }

    /**
     * Функция, отвечающая за проверку возможности нахождения игрока на определённой позиции
     *
     * @param playerPosition позиция, которую нужно проверить
     *
     * @return true, если на этой позиции игрок не находится в блоке, false в ином случае
     */
    private fun checkPosition(playerPosition: Vector2): Boolean {
        val xRange = clampProgression(playerPosition.x - 2, playerPosition.x + 2, 0, scene.sceneWidth - 1)
        val yRange = clampProgression(playerPosition.y - 2, playerPosition.y + 2, 0, scene.sceneHeight - 1)
        for (blockX in xRange) {
            for (blockY in yRange) {
                if (!scene.doesBlockExist(blockX, blockY)) continue

                val blockPosition = Vector2(blockX.toDouble(), blockY.toDouble())
                if (isPlayerInBlock(playerPosition, blockPosition)) {
                    return false
                }
            }
        }
        return true
    }

    private fun goTo(position: Vector2): Boolean {
        if (checkPosition(position)) {
            this.position = position
            return true
        }
        return false
    }

    fun goInDirection(direction: Vector2, frameTime: Double) {
        goTo((direction.unit() * frameTime * speed + this.position).round(1))

    }
}