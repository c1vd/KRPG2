package math

import blocks.DefaultBlock
import blocks.breakable.Dirt
import blocks.breakable.Mud
import blocks.unbreakable.Bedrock
import blocks.unbreakable.Unknown
import camera.Camera
import org.openrndr.math.Vector2
import java.security.SecureRandom
import kotlin.math.max
import kotlin.math.min

val secureRandom = SecureRandom()

fun getCoordinatesOfBlockOnScreen(x: Int, y: Int, camera: Camera): Vector2 {
    return (Vector2(x.toDouble(), y.toDouble()) - Vector2(
        camera.x - blocksPerSemiWidth,
        camera.y - blocksPerSemiHeight
    )) * blockSize
}

fun clamp(n: Int, a: Int, b: Int): Int {
    if (a > b) {
        throw Exception("it must be like a <= b")
    }
    return max(min(n, b), a)
}

fun randomInteger(a: Int, b: Int): Int {
    return secureRandom.nextInt(b - a + 1) + a
}

fun idToBlock(id: Int): DefaultBlock? {
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
    return a.x <= 1 && a.y <= 1 && a.x >= 0 && a.y >= 0
}

fun getCoordinatesFromMousePixelPositionAndCamera(mousePositionPixels: Vector2, camera: Camera): Vector2 {
    return (mousePositionPixels / blockSize) + (Vector2(camera.x - blocksPerSemiWidth, camera.y - blocksPerSemiHeight))
}