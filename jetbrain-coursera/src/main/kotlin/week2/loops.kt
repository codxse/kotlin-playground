package week2


val myList = listOf("a", "b", "c", "d")
fun printMyListLoop() {
    for (s in myList) {
        print(s)
    }
}

fun printListWithIndexLoop() {
    for ((index,  value) in myList.withIndex()) {
        println("Index=$index value=$value")
    }
}

// iterating over map
val mymap = mapOf(1 to "One", 2 to "Two", 3 to "Three")
fun printMyMapLoop() {
    for ((key, value) in mymap) {
        println("key=$key value=$value")
    }
}

// iterating over range
fun printRange() {
    for (i in 1..9) {
        print(i)
    }
}

