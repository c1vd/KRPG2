package blocks.blocks

import blocks.Block
import blocks.textures.mudTexture
import org.openrndr.draw.ColorBuffer

object Mud : Block {
    override val texture: ColorBuffer = mudTexture
    override val id: Int = 3
}