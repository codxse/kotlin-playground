package euler

fun main() {
    val t = fs(0, 1, 0)
    print(t)
}

fun fs(prev: Int, v: Int, curr: Int): Int {
    return if (v < 4_000_000) {
        val next = if (v % 2 == 0) v else 0
        fs(v, prev + v, curr + next)
    } else {
        curr
    }
}