package euler

fun main() {
    println("prime 10001")
    println(generatePrime().take(10_001).toList()[10_000])
}

fun isPrime2(i: Long): Boolean {
    if (i == 2L) return true
    if (i % 2 == 0L) return false
    val q = Math.ceil(Math.sqrt(i.toDouble())).toLong()
    for (iter in q downTo 2) {
        if (i % iter == 0L) return false
    }
    return true
}

fun generatePrime() = sequence {
    var next = 3L
    yield(2L)
    while (true) {
        if (isPrime2(next)) {
            yield(next)
        }
        next += 2
    }
}