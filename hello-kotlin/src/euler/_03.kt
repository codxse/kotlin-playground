package euler

fun main() {
    val q: Long = 600851475143
    val s = Math.sqrt((q / 2).toDouble()).toLong()
    for (t in s downTo 2 step 2) {
        println(t)
        if (isPrime(t)) {
            val m = t.toInt()
            if (q % m == 0.toLong()) {
                print(m)
                return
            }
        }
    }
}

fun isPrime(n: Long): Boolean {
    if (n == 2.toLong()) return true
    if (n < 2) return false
    val sq = (Math.sqrt(n.toDouble()).toInt() + 1).toLong()
    for (s in sq downTo 2) {
        if (n % s == 0.toLong()) {
            return false
        }
    }
    return true
}