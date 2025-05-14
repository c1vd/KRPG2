package entities

import extensions.round
import extensions.unit
import extensions.xVector2
import extensions.yVector2
import math.clampProgression
import math.inBlock
import org.openrndr.math.Vector2
import other.sceneHeight
import other.sceneWidth
import scene.Scene


class Player(
    override var scene: Scene,
    override var position: Vector2 = Vector2(0.0, 0.0),
    override val sizeVector: Vector2 = Vector2(1.0, 1.0),
    private var speed: Double = 5.0,
    override val name: String = "Player"
) : Entity {

    /* методы, отвечающие за движение */

    private fun isPlayerInBlock(position: Vector2, blockPosition: Vector2): Boolean {
        return inBlock(position, blockPosition) ||
                inBlock(position + sizeVector, blockPosition) ||
                inBlock(position + sizeVector.xVector2(), blockPosition) ||
                inBlock(position + sizeVector.yVector2(), blockPosition)
    }

    private fun checkPosition(position: Vector2): Boolean {
        val xRange = clampProgression(position.x - 2, position.x + 2, 0, sceneWidth - 1)
        val yRange = clampProgression(position.y - 2, position.y + 2, 0, sceneHeight - 1)
        for (blockX in xRange) {
            for (blockY in yRange) {
                if (scene.getBlock(blockX, blockY) != null) {
                    val blockPosition = Vector2(blockX.toDouble(), blockY.toDouble())
                    if (isPlayerInBlock(position, blockPosition)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun goTo(position: Vector2) {
        if (checkPosition(position)) {
            this.position = position
        }
    }

    private fun goInDirection(direction: Vector2, frametime: Double) {
        goTo((direction.unit() * frametime * speed + this.position).round(1))
    }

    fun right(frametime: Double) {
        goInDirection(Vector2(1.0, 0.0), frametime)
    }

    fun left(frametime: Double) {
        goInDirection(Vector2(-1.0, 0.0), frametime)
    }

    fun up(frametime: Double) {
        goInDirection(Vector2(0.0, -1.0), frametime)
    }

    fun down(frametime: Double) {
        goInDirection(Vector2(0.0, 1.0), frametime)
    }

}