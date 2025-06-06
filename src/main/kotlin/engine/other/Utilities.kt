package engine.other

import engine.camera.Camera
import engine.extensions.toPixelVector2
import org.openrndr.math.Vector2
import org.openrndr.math.clamp


fun getCoordinatesOnScreen(position: Vector2, camera: Camera): Vector2 =
    (position - camera.startScreenCoordinates()).toPixelVector2()


fun checkIndex(index: Int, arraySize: Int): Boolean = index in 0 until arraySize


/**
 * Функция, проверяющая находится ли точка внутри блока
 *
 * @param pointPosition координата точки, которую нужно проверить
 * @param blockPosition координата блока
 *
 * @return true если точка внутри блока, false в ином случае
 */
fun isPointInBlock(pointPosition: Vector2, blockPosition: Vector2): Boolean {
    val a = pointPosition - blockPosition
    return a.x < 1 && a.y < 1 && a.x > 0 && a.y > 0
}

fun clampInt(value: Number, min: Number, max: Number): Int {
    return clamp(value.toDouble(), min.toDouble(), max.toDouble()).toInt()
}

/**
 * Функция, нужная для создания IntProgression, которая находится в определённых пределах
 *
 * @param a
 * значение прогрессии слева
 * @param b
 * значение прогрессии справа
 * @param left
 * предел слева, не дающий a и b выходить за него
 * @param right
 * предел справа, не дающий a и b выходить за него
 */
fun clampProgression(a: Number, b: Number, left: Number, right: Number): IntProgression {
    return clampInt(
        a,
        left,
        right
    )..clampInt(
        b,
        left,
        right
    )
}
