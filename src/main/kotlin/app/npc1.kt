package app

import engine.entities.NPC
import engine.message.Message
import engine.scene.Scene
import org.openrndr.draw.ColorBuffer
import org.openrndr.math.Vector2

object npc1 : NPC() {
    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")

    override fun interact() {
        scene.messageController.addMessage(Message("I am npc1", this))
    }

    override lateinit var scene: Scene

    override val name: String
        get() = "npc1"
    override var position: Vector2
        get() = Vector2(0.0, 0.0)
        set(value) {}
    override val sizeVector: Vector2
        get() = Vector2(1.0, 1.0)
}