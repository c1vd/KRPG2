package extensions

fun String.setColor(color: String): String{
    return "$color + $this + \u001b[0m"
}