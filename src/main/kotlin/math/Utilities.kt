package math

import blocks.Block
import blocks.blocks.Dirt
import blocks.blocks.Mud
import blocks.blocks.Bedrock
import blocks.blocks.Unknown
import camera.Camera
import org.openrndr.math.Vector2
import java.security.SecureRandom
import kotlin.math.max
import kotlin.math.min

val secureRandom = SecureRandom()

fun getCoordinatesOfBlockOnScreen(blockPosition: Vector2, camera: Camera): Vector2 {
    return (blockPosition - camera.startScreenCoordinates()) * blockSize
}

fun clamp(n: Int, a: Int, b: Int): Int {
    if (a > b) {
        return clamp(n, b, a)
    }
    return max(min(n, b), a)
}

fun randomInteger(a: Int, b: Int): Int {
    return secureRandom.nextInt(b - a + 1) + a
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
 * метод, проверяющий находится ли точка внутри блока
 *
 * @param position (координата точки, которую нужно проверить)
 * @param blockPosition (координата блока)
 *
 * @return true если точка внутри блока, false в ином случае
 */
fun inBlock(position: Vector2, blockPosition: Vector2): Boolean {
    val a = position - blockPosition
    return a.x < 1 && a.y < 1 && a.x > 0 && a.y > 0
}

fun getGlobalCursorPosition(mousePositionInBlocks: Vector2, camera: Camera): Vector2 {
    return mousePositionInBlocks + camera.startScreenCoordinates()
}

fun clampProgression(a: Number, b: Number, left: Int, right: Int): IntProgression{
    if (a is Int && b is Int){
        return clamp(a, left, right)..clamp(b, left, right)
    }
    if (a is Double && b is Double){
        return clamp(a.toInt(), left, right)..clamp(b.toInt(), left, right)
    }
    throw Exception("a and b must be same type")
}