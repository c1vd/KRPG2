package blocks.blocks

import blocks.Block
import blocks.textures.dirtTexture
import org.openrndr.draw.ColorBuffer


object Dirt : Block {
    override val texture: ColorBuffer = dirtTexture
    override val id: Int = 1
}