package day7

import support.downloadInput
import support.toInts1
import kotlin.math.absoluteValue

fun main() {

    println(solve(downloadInput(7, 1)))
    println("--------------------------")
    println(solve(downloadInput(7)))

}

fun solve(input: List<String>): Any {
    val positions = input.first().split(',').toInts1()
    val distanceCosts = (1..2000).runningFold(0) { acc, index -> acc + index }

    val costs = (0..2000).map { aim ->
        positions.sumOf { distanceCosts[(it - aim).absoluteValue] }
    }
    return costs.minOf { it }
}
