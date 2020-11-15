package euler

fun main() {
    val arr = mutableListOf<Number>()
    for (i in 999 downTo 100) {
        for (j in 999 downTo 100) {
            val v = i * j
            if (isPalindrome(v.toString())) {
                arr.add(v)
            }
        }
    }
    println(arr.maxBy { it.toFloat() })
}

fun isPalindrome(s: String): Boolean {
    return s == s.reversed()
}