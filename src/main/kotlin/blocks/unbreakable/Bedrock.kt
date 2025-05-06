package blocks.unbreakable

import blocks.UnbreakableBlock
import blocks.textures.bedrockTexture
import items.BlockItem
import items.blockItems.BedrockItem
import org.openrndr.draw.ColorBuffer


object Bedrock: UnbreakableBlock {
    override val texture: ColorBuffer = bedrockTexture
    override val blockItem: BlockItem = BedrockItem
    override val id: Int = 2
}