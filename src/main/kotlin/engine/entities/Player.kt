package engine.entities

import engine.extensions.infix.multiply
import engine.extensions.round
import engine.extensions.unit
import engine.extensions.xVector2
import engine.extensions.yVector2
import engine.inventory.PlayerInventory
import engine.other.Constants
import engine.other.clampProgression
import engine.other.isPointInBlock
import org.openrndr.math.Vector2
import engine.scene.Scene


open class Player(
    override var scene: Scene,
    override var position: Vector2 = Vector2(0.0, 0.0),
    override val sizeVector: Vector2 = Vector2(1.0, 1.0),
    private var speed: Double = 5.0,
    override val name: String = "Player",
    inventorySize: Int = 10
) : Entity {
    val inventory = PlayerInventory(inventorySize)

    /**
     * Метод, проверяющий будет ли находиться игрок в определенном блоке,
     * если его передвинуть на указанные координаты
     *
     * @param playerPosition проверяемая позиция игрока
     * @param blockPosition позиция блока
     *
     * @return true, если игрок будет задевать заданный блок, false в ином
     * случае
     */
    private fun isPlayerInBlock(
        playerPosition: Vector2,
        blockPosition: Vector2
    ): Boolean {
        return isPointInBlock(playerPosition, blockPosition) ||
                isPointInBlock(playerPosition + sizeVector, blockPosition) ||
                isPointInBlock(
                    playerPosition + sizeVector.xVector2(),
                    blockPosition
                ) ||
                isPointInBlock(
                    playerPosition + sizeVector.yVector2(),
                    blockPosition
                )
    }

    /**
     * Метод, отвечающий за проверку возможности нахождения игрока на определённой позиции
     *
     * @param playerPosition позиция, которую нужно проверить
     *
     * @return true, если на этой позиции игрок не находится в блоке, false в ином случае
     */
    private fun checkPosition(playerPosition: Vector2): Boolean {
        val xRange = clampProgression(
            playerPosition.x - 2,
            playerPosition.x + 2,
            0,
            scene.sceneWidth - 1
        )
        val yRange = clampProgression(
            playerPosition.y - 2,
            playerPosition.y + 2,
            0,
            scene.sceneHeight - 1
        )

        val blocksCoordinatesToCheck = xRange multiply yRange
        for ((x, y) in blocksCoordinatesToCheck) {
            if (!scene.blocks.exists(x, y)) continue

            val blockPosition = Vector2(x.toDouble(), y.toDouble())
            if (isPlayerInBlock(playerPosition, blockPosition)) {
                return false
            }
        }
        return true
    }

    private fun goTo(position: Vector2): Boolean {
        return checkPosition(position).also { if (it) this.position = position }
    }

    fun goInDirection(direction: Vector2, frameTime: Double) {
        goTo((direction.unit() * frameTime * speed + this.position).round(1))
    }

    private fun getNPCsToInteract(): List<NPC> {
        return scene.nonPlayableCharacters.filter {
            (position - it.position).length < Constants.NPC_INTERACTION_DISTANCE
        }
    }

    private fun getClosestNPC(): NPC? {
        return getNPCsToInteract().minByOrNull {
            (position - it.position).length
        }
    }

    fun interactWithNPC() {
        getClosestNPC()?.interact()
    }
}