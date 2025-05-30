package app

import engine.entities.NPC
import engine.scene.Scene
import org.openrndr.draw.ColorBuffer
import org.openrndr.math.Vector2

object npc2 : NPC() {
    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")

    override fun interact() {
        TODO("Not yet implemented")
    }

    override var scene: Scene
        get() = TODO("Not yet implemented")
        set(value) {}
    override val name: String
        get() = "npc2"
    override var position: Vector2
        get() = Vector2(3.0, 0.0)
        set(value) {}
    override val sizeVector: Vector2
        get() = Vector2(1.0, 1.0)
}