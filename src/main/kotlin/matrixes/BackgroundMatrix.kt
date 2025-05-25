package matrixes

import background.Background

class BackgroundMatrix(width: Int, height: Int) : Matrix<Background>() {
    override val matrix: Array<Array<Background?>> = Array(height) { Array(width) { null } }
}