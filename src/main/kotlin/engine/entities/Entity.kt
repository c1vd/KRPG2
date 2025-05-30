package engine.entities

import org.openrndr.math.Vector2
import engine.scene.Scene

interface Entity{
    var scene: Scene
    val name: String
    var position: Vector2
    val sizeVector: Vector2

    /**
     * Метод, меняющий сцену, на которой находится [Entity]
     */
    fun changeScene(newScene: Scene){
        scene = newScene
    }
}