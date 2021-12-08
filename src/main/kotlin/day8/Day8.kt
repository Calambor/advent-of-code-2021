package day8

import support.downloadInput

fun main() {

    println(solve(downloadInput(8, 1)))
    println("--------------------------")
    println(solve(downloadInput(8)))

}

val realDigits = mapOf(
    listOf(1, 2, 3, 5, 6, 7) to 0,
    listOf(3, 6) to 1,
    listOf(1, 3, 4, 5, 7) to 2,
    listOf(1, 3, 4, 6, 7) to 3,
    listOf(2, 3, 4, 6) to 4,
    listOf(1, 2, 4, 6, 7) to 5,
    listOf(1, 2, 4, 5, 6, 7) to 6,
    listOf(1, 3, 6) to 7,
    listOf(1, 2, 3, 4, 5, 6, 7) to 8,
    listOf(1, 2, 3, 4, 6, 7) to 9,
)

fun solve(input: List<String>): Any {
    val problems = input.map { it.split(" | ") }

    val results = problems.map { problem ->
        val unscramble = mutableMapOf<Int, Int>()
        val digits = problem[0].split(' ').map { it.toCharArray().map { it.ordinal } }
        val counts = (0..7).map { ordinal -> digits.flatten().count { it == ordinal } }
        unscramble[counts.indexOf(6)] = 2
        unscramble[counts.indexOf(4)] = 5
        unscramble[counts.indexOf(9)] = 6

        unscramble[
                digits.find { it.size == 2 }!!.find { it !in unscramble.keys }!!
        ] = 3
        unscramble[
                digits.find { it.size == 3 }!!.find { it !in unscramble.keys }!!
        ] = 1
        unscramble[
                digits.find { it.size == 4 }!!.find { it !in unscramble.keys }!!
        ] = 4
        unscramble[(1..7).find { it !in unscramble.keys }!!] = 7

        val out = problem[1].split(' ').map { it.toCharArray().map { it.ordinal } }
        val outDigits = out.map {
            realDigits[it.map {
                unscramble[it]!!
            }.sorted()]!!
        }
        outDigits.fold(0) { acc, digit -> acc * 10 + digit }
    }
    return results.sum()
}

/** a-f -> 1-7 */
val Char.ordinal get() = this.digitToInt(17) - 9
