package engine.matrixes

abstract class Matrix<T> {
    protected abstract val matrix: Array<Array<T?>>
    fun get(x: Int, y: Int): T?{
        return try{
            matrix[y][x]
        }catch (_: Exception){
            null
        }
    }

    fun set(something: T, x: Int, y: Int){
        try {
            matrix[y][x] = something
        }catch (_: Exception){}
    }

    fun exists(x: Int, y: Int): Boolean{
        return get(x, y) != null
    }
}