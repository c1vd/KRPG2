package camera

import entities.Player
import math.blocksPerSemiHeight
import math.blocksPerSemiWidth
import org.openrndr.math.Vector2

class Camera(private var player: Player) {
    var position: Vector2 = player.position

    // изменение координат камеры на более близкие к игроку
    fun moveCamera() {
        position = player.position
    }

    /**
     * метод, меняющий игрока, на которого нужно смотреть
     *
     * @param newPlayer игрок, за которым нужно смотреть
     */
    fun changePlayer(newPlayer: Player) {
        player = newPlayer
    }

    fun startScreenCoordinates(): Vector2{
        return Vector2(position.x -  blocksPerSemiWidth, position.y - blocksPerSemiHeight)
    }
}