package week5

fun fibonacci(): Sequence<Int> = sequence {
    var n = Pair(0, 1)
    while (true) {
        yield(n.first)
        n = Pair(n.second, n.first + n.second)
    }
}

fun show() {
    fibonacci().take(10).toList()
}