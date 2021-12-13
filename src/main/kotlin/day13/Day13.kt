package day13

import support.downloadInput
import support.splitOn


fun main() {

    println("--------------------------")
    println(solve(downloadInput(13)))

}

fun solve(input: List<String>): Any {
    val paper = List(447 * 2 + 1) { MutableList(655 * 2 + 1) { '.' } }
    val (coords, folds) = input.splitOn { it.isEmpty() }
    coords.forEach { coord ->
        val (x, y) = coord.split(',').map { it.toInt() }
        paper[y][x] = '#'
    }
    var folded: List<List<Char>> = paper

    folds.forEach { fold ->
        val isX = fold[11] == 'x'
        val size = fold.substring(13).toInt()
        folded = if (isX) {
            folded.foldX(size)
        } else {
            folded.foldY(size)
        }
    }
    folded.forEach {
        println(it.toString())
    }

    return folded.flatten().count { it == '#' }
}

fun List<List<Char>>.foldX(x: Int): List<List<Char>> {
    return this.map { line ->
        val below = line.subList(0, x)
        val above = line.subList(x + 1, line.size).reversed()
        below.zip(above) { a, b ->
            if (a == '#' || b == '#') '#' else '.'
        }
    }
}

fun List<List<Char>>.foldY(y: Int): List<List<Char>> {
    val below = subList(0, y)
    val above = subList(y + 1, size).reversed()
    return below.zip(above) { a, b ->
        a.zip(b) { a, b ->
            if (a == '#' || b == '#') '#' else '.'
        }
    }
}
