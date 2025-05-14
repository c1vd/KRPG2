package entities

import org.openrndr.math.Vector2
import scene.Scene

interface Entity{
    var scene: Scene
    val name: String
    var position: Vector2
    val sizeVector: Vector2
}