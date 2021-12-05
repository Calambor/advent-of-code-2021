package day5

import support.downloadInput
import support.toInts1
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {

    println(solve(downloadInput(5, 1)))
    println("--------------------------")
    println(solve(downloadInput(5)))

}

fun solve(input: List<String>): Any {
    val grid = List(1000) { MutableList(1000){0} }
    val lines = input.map { it.split(" -> ").flatMap { it.split(',').toInts1() } }
    val straights = lines.filter { it[0] == it[2] || it[1] == it[3] }
    val diagonals = lines - straights
    for (line in straights) {
        val xRange = min(line[0], line[2])..max(line[0], line[2])
        val yRange = min(line[1], line[3])..max(line[1], line[3])
        xRange.forEach { x ->
            yRange.forEach { y ->
                grid[x][y] = grid[x][y] + 1
            }
        }
    }
    for (line in diagonals) {
        val xDir = (line[2] - line[0]).sign
        val yDir = (line[3] - line[1]).sign
        (0..(line[0]-line[2]).absoluteValue).forEach { step ->
            val x = line[0] + step * xDir
            val y = line[1] + step * yDir
            grid[x][y] = grid[x][y] + 1
        }
    }

    return grid.flatten().count { it >= 2 }
}
