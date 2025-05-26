package engine.extensions

fun String.setColor(color: String): String{
    return "$color + $this + \u001b[0m"
}

/**
 * Расширение для строки, с помощью которого не надо писать, что разделителем является пробел
 */
fun String.split(): List<String>{
    return this.split(' ')
}