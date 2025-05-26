package engine.extensions.infix

import engine.other.IntCoordinates


infix fun IntProgression.multiply(other: IntProgression): List<IntCoordinates> {
    val lst = mutableListOf<IntCoordinates>()
    for (i in this) {
        for (j in other) {
            lst.add(IntCoordinates(i, j))
        }
    }
    return lst
}