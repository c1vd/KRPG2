package engine.inventory

import engine.items.Food

class PlayerInventory(size: Int) : Inventory() {
    override val items: Array<Food?> = Array(size) { null }
}