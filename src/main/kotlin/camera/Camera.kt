package camera

import entities.Player

class Camera(private var player: Player) {
    var x: Double = player.x
    var y: Double = player.y

    // изменение координат камеры на более близкий к игроку
    fun moveCamera() {
        x += (player.x - x) // / 15
        y += (player.y - y) // / 15
    }

    fun changePlayer(newPlayer: Player) {
        player = newPlayer
    }
}