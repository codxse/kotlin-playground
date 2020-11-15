package week2

// PART 1: if & when
// ==============================

// if as an expresion
val b = 2
val a = if (b > 3) 1 else 2

// when
val color = "blue"
val s = when (color) {
    "blue" -> 1
    "green" -> 2
    "red" -> 3
    else -> 0
}

val p = when {
    s > 0 -> "x"
    s < 0 -> "z"
    else -> "zero"
}

fun mix(c1: String, c2: String): Boolean {
    return when (setOf(c1, c2)) {
        setOf("a", "b") -> true
        setOf("c", "d") -> true
        setOf("x", "z") -> true
        else -> throw Exception("not valid set")
    }
}

// Checking identifier
// =========================
// Implement the function that checks whether a string is a valid identifier.
// A valid identifier is a non-empty string that starts with a letter or underscore
// and consists of only letters, digits and underscores.

val chars = ('a'..'z').toList().toTypedArray()
val digits = ('0'..'9').toList().toTypedArray()
val validChars = listOf<Char>('_', *chars, *digits)

fun isValidIdentifier(s: String): Boolean {
    if (s == "" || s.first() == '0') {
        return false
    }
    return s.all { it in validChars }
}

fun testIt() {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}

