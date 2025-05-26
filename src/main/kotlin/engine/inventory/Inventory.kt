package engine.inventory

import engine.items.Food
import engine.other.checkIndex

abstract class Inventory {
    protected abstract val items: Array<Food?>

    /* функции, отвечающие за добавление и получение предметов из инвентаря */

    fun add(item: Food): Boolean {
        if (!items.contains(null)) {
            return false
        }
        items[items.indexOf(null)] = item
        return true
    }

    fun get(index: Int): Food? {
        if(!checkIndex(index, items.size)){
            return null
        }
        return items[index].also {
            items[index] = null
        }
    }

    fun at(index: Int): Food? {
        if(!checkIndex(index, items.size)){
            return null
        }
        return items[index]
    }
}