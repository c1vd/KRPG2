package blocks.blocks

import blocks.Block
import blocks.textures.bedrockTexture
import org.openrndr.draw.ColorBuffer


object Bedrock: Block {
    override val texture: ColorBuffer = bedrockTexture
    override val id: Int = 2
}