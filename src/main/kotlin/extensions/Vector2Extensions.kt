package extensions

import other.blockSize
import org.openrndr.math.Vector2
import org.openrndr.panel.elements.round

fun Vector2.toBlockVector2(): Vector2 {
    return this / blockSize
}

fun Vector2.toPixelVector2(): Vector2 {
    return this * blockSize
}

fun Vector2.xVector2(): Vector2{
    return Vector2(x, 0.0)
}

fun Vector2.yVector2(): Vector2{
    return Vector2(0.0, y)
}

fun Vector2.unit(): Vector2{
    return this/length
}

fun Vector2.round(n: Int): Vector2{
    return Vector2(x.round(n), y.round(n))
}