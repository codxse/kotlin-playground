package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    return (0 until permutation.size).flatMap { i ->
        val j = i + 1
        (j until permutation.size).map {
            permutation[i] > permutation[it]
        }
    } .count { it } % 2 == 0
}