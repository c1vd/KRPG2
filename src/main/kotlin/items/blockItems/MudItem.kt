package items.blockItems

import blocks.DefaultBlock
import blocks.breakable.Mud
import items.BlockItem
import org.openrndr.draw.ColorBuffer

object MudItem : BlockItem {
    override val block: DefaultBlock = Mud
    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")
}