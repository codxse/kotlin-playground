package euler

fun main () {
    var v: Long = 1
    for (i in 1..20) {
        v = lcm(v, i.toLong())
    }
    println(v)
    val pure = loop(1, 1)
    println("pure $pure")
}

fun loop(v: Long, i: Long): Long {
    if (i < 20) {
        return loop(lcm(v, i), i + 1)
    }
    return v
}

var longZero = 0.toLong()

fun gcd(a: Long, b: Long): Long {
    if (b <= longZero) {
        return a
    }
    return gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    return (a * b) / gcd(a, b)
}