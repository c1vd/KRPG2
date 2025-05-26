package engine.blocks

import org.openrndr.draw.ColorBuffer

interface Block{
    val texture: ColorBuffer
    val id: Int
}