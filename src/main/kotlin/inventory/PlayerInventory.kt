package inventory

import items.Food

class PlayerInventory : Inventory() {
    override val items: Array<Food?> = Array(10){null}
}