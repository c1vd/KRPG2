package items.blockItems

import blocks.DefaultBlock
import blocks.unbreakable.Bedrock
import items.BlockItem
import org.openrndr.draw.ColorBuffer

object BedrockItem : BlockItem {
    override val block: DefaultBlock = Bedrock
    override val texture: ColorBuffer
        get() = TODO("Not yet implemented")
}