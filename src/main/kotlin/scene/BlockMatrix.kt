package scene

import blocks.Block

class BlockMatrix(width: Int, height: Int) {
    val matrix: Array<Array<Block?>> = Array(height) { Array(width) { null } }
    fun get(x: Int, y: Int): Block?{
        return try{
            matrix[y][x]
        }catch (_: Exception){
            null
        }
    }

    fun set(blockToSet: Block, x: Int, y: Int){
        try {
            matrix[y][x] = blockToSet
        }catch (_: Exception){}
    }
}