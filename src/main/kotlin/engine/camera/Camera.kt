package engine.camera

import engine.entities.Player
import engine.other.blocksPerSemiHeight
import engine.other.blocksPerSemiWidth
import org.openrndr.math.Vector2

class Camera(private var player: Player) {
    private var position: Vector2 = player.position
    val x: Double
        get() = position.x
    val y: Double
        get() = position.y
    // изменение координат камеры на более близкие к игроку
    fun moveCamera() {
        position = player.position
    }

    /**
     * Метод, меняющий игрока, на которого нужно смотреть
     *
     * @param newPlayer игрок, за которым нужно смотреть
     */
    fun changePlayer(newPlayer: Player) {
        player = newPlayer
    }

    fun startScreenCoordinates(): Vector2{
        return Vector2(x -  blocksPerSemiWidth, y - blocksPerSemiHeight)
    }
}