package blocks.breakable

import blocks.BreakableBlock
import blocks.textures.dirtTexture
import items.BlockItem
import items.Item
import items.blockItems.DirtItem
import org.openrndr.draw.ColorBuffer


object Dirt : BreakableBlock {
    override val texture: ColorBuffer = dirtTexture
    override val strength = 5.0
    override val blockItem: BlockItem = DirtItem
    override val id: Int = 1
}