package items.blockItems

import blocks.DefaultBlock
import blocks.breakable.Dirt
import items.BlockItem
import org.openrndr.draw.ColorBuffer

object DirtItem : BlockItem {
    override val block: DefaultBlock = Dirt
    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")
}