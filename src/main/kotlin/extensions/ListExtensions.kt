package extensions


fun List<String>.toIntArray(): List<Int> {
    return this.map { it.toInt() }
}