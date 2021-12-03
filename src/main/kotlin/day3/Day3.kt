package day3

import support.downloadInput

fun main() {

    println(solve(downloadInput(3, 1)))
    println("--------------------------")
    println(solve(downloadInput(3)))

}

fun solve(input: List<String>): Any {
    var oxygen = input
    (0 until input[0].length).forEach { index ->
        if (oxygen.size > 1) {
            val ones = oxygen.count { it[index] == '1' }
            val keptLetter = if (ones * 2 >= oxygen.count()) '1' else '0'
            oxygen = oxygen.filter { it[index] == keptLetter }
        }
    }
    var co2 = input
    (0 until input[0].length).forEach { index ->
        if (co2.size > 1) {
            val ones = co2.count { it[index] == '1' }
            val keptLetter = if (ones * 2 >= co2.count()) '0' else '1'
            co2 = co2.filter { it[index] == keptLetter }
        }
    }
    return oxygen.first().toInt(2) * co2.first().toInt(2)
}

fun solveP1(input: List<String>): Any {
    val gamma = (0 until input[0].length).map { index ->
        val ones = input.count { it[index] == '1' }
        if (ones > input.size / 2) {
            '1'
        } else {
            '0'
        }
    }.joinToString("")
    val epsilon = gamma.map { if (it == '1') '0' else '1' }.joinToString("")

    return gamma.toInt(2) * epsilon.toInt(2)
}
