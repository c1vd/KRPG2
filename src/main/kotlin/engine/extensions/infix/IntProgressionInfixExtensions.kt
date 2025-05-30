package engine.extensions.infix

import org.openrndr.math.IntVector2


infix fun IntProgression.multiply(other: IntProgression): List<IntVector2> {
    val lst = mutableListOf<IntVector2>()
    for (i in this) {
        for (j in other) {
            lst.add(IntVector2(i, j))
        }
    }
    return lst
}