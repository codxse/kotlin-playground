package week2


// PART 1: Function definition
// =================================

// Function that return value
fun max(n1: Int, n2: Int): Int {
    return if (n1 > n2) {
        n1
    } else {
        n2
    }
}

fun max2(n1: Int, n2: Int) = if (n1 > n2) n1 else n2

// Function that do side effect, void
fun printHello() {
    println("Hello")
}

// same as

fun printHello2(): Unit {
    println("Hello")
}

// PART 2: Default value and named parameter
// ==========================================

val strNumber = listOf(1, 2, 3).joinToString(separator = "", prefix = "(", postfix = ")")

fun displaySeparator(char: Char = '*', times: Int = 10) {
    repeat(times) {
        print(char)
    }
}

fun sum3(n1: Int = 0, n2: Int = 0, n3: Int = 3) = n1 + n2 + n3

// PART 3: Extension function
// ================================

fun String.rest() = this.drop(1)
fun List<Any>.rest() = this.drop(1)

// Sum as an extension function
// Change the 'sum' function so that it was declared as an extension to List<Int>.
// ===================================

fun List<Int>.sum() = this.reduce { acc, now -> acc + now }


