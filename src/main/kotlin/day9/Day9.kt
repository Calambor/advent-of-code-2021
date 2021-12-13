package day9

import support.downloadInput

fun main() {

    println(solve(downloadInput(9, 1)))
    println("--------------------------")
    println(solve(downloadInput(9)))

}

const val PASSED = 10

fun solve(input: List<String>): Any {
    val heights = input.map { it.map { it.digitToInt() }.toMutableList() }
    val fields = mutableListOf<Int>()
    val threat = (heights.indices).sumOf { x ->
        (heights.first().indices).sumOf { y ->
            val curr = heights[x][y]
            if (curr < 9) {
                fields.add(heights.flood(x, y))
            }
            if (
                (x == 0 || curr < heights[x - 1][y]) &&
                (x == heights.size - 1 || curr < heights[x + 1][y]) &&
                (y == 0 || curr < heights[x][y - 1]) &&
                (y == heights.first().size - 1 || curr < heights[x][y + 1])
            ) {
                curr + 1
            } else {
                0
            }
        }
    }

    return fields.sorted().takeLast(3).fold(1) { acc, i ->
        acc * i
    }
}

fun List<MutableList<Int>>.flood(x: Int, y: Int): Int {
    this[x][y] = PASSED
    return 1 + (if (x != 0 && this[x - 1][y] < 9) flood(x - 1, y) else 0) +
            (if (x != size - 1 && this[x + 1][y] < 9) flood(x + 1, y) else 0) +
            (if (y != 0 && this[x][y - 1] < 9) flood(x, y - 1) else 0) +
            (if (y != first().size - 1 && this[x][y + 1] < 9) flood(x, y + 1) else 0)
}
