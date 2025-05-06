package items.blockItems

import blocks.DefaultBlock
import blocks.unbreakable.Unknown
import items.BlockItem
import org.openrndr.draw.ColorBuffer

object UnknownItem : BlockItem {
    override val block: DefaultBlock = Unknown
    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")
}