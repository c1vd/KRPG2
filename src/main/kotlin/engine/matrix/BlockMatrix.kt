package engine.matrix

import engine.blocks.Block

class BlockMatrix(width: Int, height: Int) : Matrix<Block>() {
    override val matrix: Array<Array<Block?>> = Array(height) { Array(width) { null } }
}