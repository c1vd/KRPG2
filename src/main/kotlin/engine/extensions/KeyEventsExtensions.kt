package engine.extensions

import org.openrndr.KeyEvents

fun KeyEvents.setKeyDown(key: Int, function: () -> Unit) {
    this.keyDown.listen {
        if (it.key == key) function()
    }
}

fun KeyEvents.setKeyUp(key: Int, function: () -> Unit) {
    this.keyUp.listen {
        if (it.key == key) function()
    }
}