package other

import blocks.Block
import blocks.blocks.Dirt
import blocks.blocks.Mud
import blocks.blocks.Bedrock
import blocks.blocks.Unknown
import camera.Camera
import org.openrndr.math.Vector2
import org.openrndr.math.clamp
import java.security.SecureRandom
import kotlin.math.max
import kotlin.math.min


fun getCoordinatesOnScreen(position: Vector2, camera: Camera): Vector2 {
    return (position - camera.startScreenCoordinates()) * blockSize
}


fun checkIndex(index: Int, arraySize: Int): Boolean {
    return index in 0..<arraySize
}

fun idToBlock(id: Int): Block? {
    return when (id) {
        0 -> Unknown
        1 -> Dirt
        2 -> Bedrock
        3 -> Mud
        else -> null
    }
}

/**
 * Функция, проверяющая находится ли точка внутри блока
 *
 * @param position координата точки, которую нужно проверить
 * @param blockPosition координата блока
 *
 * @return true если точка внутри блока, false в ином случае
 */
fun inBlock(position: Vector2, blockPosition: Vector2): Boolean {
    val a = position - blockPosition
    return a.x < 1 && a.y < 1 && a.x > 0 && a.y > 0
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
    return clamp(
        a.toDouble(),
        left.toDouble(),
        right.toDouble()
    ).toInt()..clamp(
        b.toDouble(),
        left.toDouble(),
        right.toDouble()
    ).toInt()
}

