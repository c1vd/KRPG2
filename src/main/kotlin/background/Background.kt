package background

import org.openrndr.draw.ColorBuffer

interface Background {
    val id: Int
    val texture: ColorBuffer
}