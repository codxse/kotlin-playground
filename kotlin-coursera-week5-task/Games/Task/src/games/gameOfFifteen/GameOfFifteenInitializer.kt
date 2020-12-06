package games.gameOfFifteen

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val temp= (1..15).shuffled()

        if (!isEven(temp)) temp.swapValues()

        temp
    }

    private fun <T> List<T>.swapValues(index1: Int = 0, index2: Int = 1): List<T> {
        val result = this.toMutableList()
        val aux = result[index1]
        result[index1] = result[index2]
        result[index2] = aux
        return result
    }
}

