package scene

import background.Background

class BackgroundMatrix(width: Int, height: Int) {
    val matrix: Array<Array<Background?>> = Array(height) { Array(width) { null } }
    fun get(x: Int, y: Int): Background? {
        return try {
            matrix[y][x]
        } catch (_: Exception) {
            null
        }
    }

    fun set(backgroundToSet: Background, x: Int, y: Int) {
        try {
            matrix[y][x] = backgroundToSet
        } catch (_: Exception) {
        }
    }
}