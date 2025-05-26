package engine.entities

import org.openrndr.draw.ColorBuffer

abstract class NPC : Entity {
    abstract val texture: ColorBuffer
    abstract fun interact()
}