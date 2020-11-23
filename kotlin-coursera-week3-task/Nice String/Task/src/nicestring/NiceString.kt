package nicestring

typealias NiceStringFn = (s: String) -> Boolean

val f1: NiceStringFn = { l: String -> l.windowed(2, 1).none { listOf("bu", "ba", "be").contains(it) } }
val f1s: NiceStringFn = { l: String -> setOf("bu", "ba", "be").none { l.contains(it) }}
val f1q: NiceStringFn = { l: String -> l.zipWithNext().none { listOf("bu", "ba", "be").contains(it)} }
val f2: NiceStringFn = { l: String -> l.filter { "aiueo".contains(it) } .length >= 3 }
val f2s: NiceStringFn = { l: String -> l.count { it in "aiueo" } >= 3 }
val f3: NiceStringFn = { l: String -> l.toList().filterIndexed { index, c -> c == l.toList().getOrNull(index - 1) }.isNotEmpty() }
val f3s: NiceStringFn = { l: String -> l.zipWithNext().any { it.first == it.second }}

fun String.isNice(): Boolean {
    return listOf(f1(this), f2(this), f3(this)).filter { it == true } .size >= 2
}