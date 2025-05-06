package inventory

import items.Item

class PlayerInventory : DefaultInventory() {
    override val items: Array<Item?> = Array(10){null}
}