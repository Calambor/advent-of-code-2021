package day4

import support.downloadInput
import support.toInts1

val MARKED = 100


fun main() {

    println(solve(downloadInput(4, 1)))
    println("--------------------------")
    println(solve(downloadInput(4)))

}

fun solve(input: List<String>): Any {
    val picks = input.first().split(',').toInts1()

    var boards = input.drop(1).chunked(6) {
        it.drop(1).map {
            it.chunked(3) { it.trim().toString().toInt() }.toMutableList()
        }
    }

    for (pick in picks) {
        boards.forEach { it.markValue(pick) }
        val newWinners = boards.filter { it.hasWon() }
        boards = boards - newWinners
        if (boards.isEmpty()) {
            return pick * newWinners.first().sumUnmarked()
        }
    }

    return 0
}

fun List<MutableList<Int>>.markValue(pick: Int) {
    find { it.contains(pick) }?.run {
        val col = indexOf(pick)
        set(col, pick + MARKED)
    }
}

fun List<MutableList<Int>>.hasWon(): Boolean =
    count { it.all { it >= MARKED } } >= 1 ||
            (0..4).any { col ->
                (0..4).all { row ->
                    get(row)[col] >= MARKED
                }
            }

fun List<MutableList<Int>>.sumUnmarked(): Int =
    flatten().filter { it < MARKED }.sum()
