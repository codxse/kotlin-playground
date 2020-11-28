package rationals
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

data class Rational (val numerator: BigInteger, val denominator: BigInteger) {

    init {
        if (this.denominator == ZERO) {
            throw IllegalArgumentException()
        }
    }

    operator fun rangeTo(rational: Rational): Pair<Rational, Rational> {
        val g = gcd(this.numerator, this.denominator)
        return Pair(Rational(this.numerator/g, this.denominator/g ), rational)
    }

    operator fun compareTo(other: Rational): Int {
        val (left, right) = commonDenominators(this, other)
        return when {
            left.numerator > right.numerator -> 1
            left.numerator < right.numerator -> -1
            else -> 0
        }
    }

    private fun commonDenominators(left: Rational, right: Rational): Pair<Rational, Rational> =
            Rational(left.numerator * right.denominator, left.denominator * right.denominator) to
                    Rational(right.numerator * left.denominator, right.denominator * left.denominator)

    override fun toString(): String {
        if ((this.numerator % this.denominator) == ZERO) {
            return this.numerator.div(this.denominator).toString()
        }
        val sgn = denominator.signum().toBigInteger()
        val gcd = gcd(numerator.abs(), denominator.abs())

        return "${sgn * this.numerator / gcd}/${sgn * this.denominator / gcd}"
    }
}

infix fun BigInteger.divBy(d: BigInteger): Rational {
    val g = gcd(this.abs(), d.abs())
    return Rational(this / g, d / g)
}

infix fun Long.divBy(that: Long): Rational {
    val n = this.toBigInteger()
    val d = that.toBigInteger()
    val g = gcd(n, d)
    return Rational(n / g, d / g)
}

infix fun Int.divBy(that: Int) =
        this.toBigInteger() divBy that.toBigInteger()

operator fun Rational.plus(arg: Rational): Rational {
    val n1 = this.numerator
    val d1 = this.denominator
    val n2 = arg.numerator
    val d2 = arg.denominator

    val n = n1 * d2 + n2 * d1
    val d = d2 * d1
    return Rational(n, d);
}

operator fun Rational.minus(arg: Rational): Rational {
    return this.plus(Rational(arg.numerator.times(ZERO - ONE), arg.denominator))
}

operator fun Rational.times(arg: Rational): Rational {
    return Rational(this.numerator.times(arg.numerator), this.denominator.times(arg.denominator))
}

operator fun Rational.div(arg: Rational): Rational {
    return Rational(this.numerator.times(arg.denominator), arg.numerator.times(this.denominator))
}

operator fun Rational.unaryMinus() = Rational(this.numerator.times(ZERO - ONE), this.denominator)

fun String.toRational(): Rational {
    val (n, d) = try {
        if ("/" in this) split("/").map(::BigInteger)
        else listOf(BigInteger(this), ONE)
    } catch (e: Exception) {
        throw IllegalArgumentException("Expecting 'n/d' or 'n', but got $this", e)
    }

    val g = gcd(n, d)
    return Rational(n / g, d / g)
}

//operator fun Rational.compareTo(r: Rational): Int {
//    val g = gcd(this.denominator, this.numerator)
//    val rational = Rational(this.numerator/g, this.denominator/g)
//    if (rational.denominator == r.denominator){
//        if (rational.numerator == r.denominator) return 0
//        if (rational.numerator < r.denominator) return -1
//        return 1
//    }
//
//    val a = rational.numerator * r.denominator
//    val b = r.numerator * rational.denominator
//    if (a == b) return 0
//    if (a < b) return -1
//    return 1
//}

operator fun Pair<Rational, Rational>.contains(rational: Rational): Boolean {
    if (rational >= this.first && rational <= this.second) return true
    return false
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}

private fun gcd(a: BigInteger, b: BigInteger): BigInteger =
        if (a % b == ZERO) b
        else gcd(b, a % b)