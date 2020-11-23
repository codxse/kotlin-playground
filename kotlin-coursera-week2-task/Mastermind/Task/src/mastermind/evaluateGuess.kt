package mastermind
import kotlin.math.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun getCorrectAnswer(secret: String, guess: String): Int {
    return secret.foldIndexed(0) {
        index, acc, current ->
        if (current == guess[index]) acc + 1 else acc
    }
}

fun getWrongAnswer(secret: String, guess: String): Int {
    val filteredSecret = secret.foldIndexed("") { i, acc, c -> if (c != guess[i]) acc + c else acc }
    val filteredGuess = guess.foldIndexed("") { i, acc, c -> if (c != secret[i]) acc + c else acc }
    return guess.fold(Pair("", 0)) {
        acc, c ->
        if (!acc.first.contains(c)) {
            val nSecret = filteredSecret.filter { it == c } .length
            val nGuess = filteredGuess.filter { it == c } .length
            Pair(acc.first + c, acc.second + min(nSecret, nGuess))
        } else {
            acc
        }
    }.second
}

fun evaluateGuess(secret: String, guess: String): Evaluation {
    return Evaluation(getCorrectAnswer(secret, guess), getWrongAnswer(secret, guess))
}