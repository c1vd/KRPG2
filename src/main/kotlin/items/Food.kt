package items

import org.openrndr.draw.ColorBuffer


// interface for all the items that can be in inventory
interface Food{
    val healthRestoration: Double
    val texture: ColorBuffer
}