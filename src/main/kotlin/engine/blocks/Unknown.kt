package engine.blocks

import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage

object Unknown : Block {
    override val id: Int = 0
    override val texture: ColorBuffer = loadImage("data/images/unknown.jpg")
}