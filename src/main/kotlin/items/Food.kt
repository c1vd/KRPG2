package items

import org.openrndr.draw.ColorBuffer


// interface for all the items that can be in inventory
interface Food{
    val id: Int
    val name: String
    val healthRestoration: Double
}