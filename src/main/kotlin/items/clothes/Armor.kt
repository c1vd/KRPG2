package items.clothes

import items.Clothes


// интерфейс для всех предметов, которые можно надеть и они защищают, но не являются аксессуарами
interface Armor : Clothes {
    val protection: Double
}