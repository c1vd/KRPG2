package blocks.breakable

import blocks.BreakableBlock
import blocks.textures.mudTexture
import items.BlockItem
import org.openrndr.draw.ColorBuffer

object Mud : BreakableBlock {
    override val strength: Double = 1.0
    override val texture: ColorBuffer = mudTexture
    override val blockItem: BlockItem
        get() = TODO("Not yet implemented")
    override val id: Int = 3
}