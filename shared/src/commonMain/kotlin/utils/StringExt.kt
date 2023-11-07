package utils

fun String.capitalize(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.capitalize() }
