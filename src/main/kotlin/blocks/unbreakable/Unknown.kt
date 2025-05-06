package blocks.unbreakable

import blocks.UnbreakableBlock
import items.BlockItem
import items.Item
import items.blockItems.UnknownItem
import org.openrndr.draw.ColorBuffer

object Unknown : UnbreakableBlock {
    override val texture: ColorBuffer = TODO()
    override val blockItem: BlockItem = UnknownItem
    override val id: Int = 0
}