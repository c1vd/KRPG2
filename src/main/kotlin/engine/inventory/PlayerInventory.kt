package engine.inventory

import engine.items.Food

class PlayerInventory : Inventory() {
    override val items: Array<Food?> = Array(10){null}
}