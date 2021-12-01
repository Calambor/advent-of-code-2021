package day1

import support.downloadInput
import support.toInts1

fun main() {

    //println(solve(downloadInput(1, 1)))
    println("--------------------------")
    println(solve(downloadInput(1)))

}

fun solve(input: List<String>): Any {
    return input.toInts1().windowed(4).fold(0) { acc, depths ->
        acc + if (depths.subList(0, 3).sum() < depths.subList(1, 4).sum()) 1 else 0
    }
}
