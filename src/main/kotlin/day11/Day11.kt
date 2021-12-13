package day11

import support.downloadInput
import java.lang.Integer.max
import java.lang.Integer.min


fun main() {

    println(solve(downloadInput(11, 1)))
    println("--------------------------")
    println(solve(downloadInput(11)))

}

fun solve(input: List<String>): Any {
    val octos = input.map { it.map { char -> char.digitToInt() }.toMutableList() }
    var stepFlashes = 0
    var step = 0
    while (stepFlashes != 100) {
        step++
        stepFlashes = 0
        octos.forEach { it.onEachIndexed { index, value -> it[index] = value + 1 } }
        var newFlashes = octos.flash()
        while (newFlashes > 0) {
            stepFlashes += newFlashes
            newFlashes = octos.flash()
        }
    }

    return step
}

fun List<MutableList<Int>>.flash(): Int {
    var flashes = 0
    this.indices.forEach { x ->
        this.first().indices.forEach { y ->
            if (this[x][y] >= 10) {
                this[x][y] = 0
                flashes++
                (max(x - 1, 0)..min(x + 1, this.size - 1)).forEach { fx ->
                    (max(y - 1, 0)..min(y + 1, this.first().size - 1)).forEach { fy ->
                        if (this[fx][fy] != 0) {
                            this[fx][fy] = this[fx][fy] + 1
                        }
                    }
                }
            }
        }
    }
    return flashes
}
