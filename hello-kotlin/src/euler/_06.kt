package euler

fun main() {
    val res = powOfSum(100) - sumOfSum(100)
    print(res)
}

fun powOfSum(upto: Long): Long {
    val d: Long = (upto / 2)
    val s: Long = (1.toLong() + upto)
    if (upto % 2 == 1.toLong()) {
        return Math.pow((d * s + d + 1).toDouble(), 2.0).toLong()
    }
    return Math.pow((d * s).toDouble(), 2.0).toLong()
}

val acc = { a: Long, n: Long -> a + Math.pow(n.toDouble(), 2.0).toLong() }

fun sumOfSum(upto: Long): Long {
    val listUpTo100 = lazyLong(0).take(upto.toInt() + 1).toList()
    return listUpTo100.reduce(acc)
}

fun lazyLong(upto: Long): Sequence<Long> {
    return generateSequence(upto, { it + 1 } )
}