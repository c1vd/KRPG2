package extensions


fun List<String>.toIntList(): List<Int> {
    return this.map { it.toInt() }
}