package euler

fun main() {
    val total3: Int = sq(1000, 0, 3, 0)
    // println(total3)
    val total5: Int = sq(1000, 0, 5, 0)
    // println(total5)
    val total15: Int = sq(1000, 0, 15, 0)
    // println(total15)
    println(total3 + total5 - total15);
}

fun sq(max: Int, i: Int, multiplier: Int, sum: Int): Int {
    val iterate = i + multiplier
    return if (iterate < max) {
        // println(iterate)
        sq(max, iterate, multiplier, sum + iterate)
    } else {
        sum
    }
}