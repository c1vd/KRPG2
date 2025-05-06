package blocks

import items.BlockItem
import org.openrndr.draw.ColorBuffer

interface DefaultBlock{
    val texture: ColorBuffer
    val blockItem: BlockItem
    val id: Int
}