package engine.entities

import org.openrndr.math.Vector2
import engine.scene.Scene

object ErrorReporter: Entity {
    override var scene: Scene
        get() = TODO("Not yet implemented")
        set(value) {}
    override val name: String = "Error Reporter"
    override var position: Vector2 = Vector2(0.0, 0.0)
    override val sizeVector: Vector2
        get() = TODO("Not yet implemented")
}