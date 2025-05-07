package inventory

import items.Item

class PlayerInventory : Inventory() {
    override val items: Array<Item?> = Array(10){null}
}