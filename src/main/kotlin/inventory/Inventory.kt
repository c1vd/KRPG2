package inventory

import items.Item

abstract class Inventory {
    abstract val items: Array<Item?>

    /* функции, отвечающие за добавление и получение предметов из инвентаря */

    fun add(item: Item): Boolean {
        if (!items.contains(null)) {
            return false
        }
        items[items.indexOf(null)] = item
        return true
    }

    fun get(index: Int): Item? {
        return try {
            items[index].also {
                items[index] = null
            }
        } catch (e: Exception) {
            null
        }
    }
}