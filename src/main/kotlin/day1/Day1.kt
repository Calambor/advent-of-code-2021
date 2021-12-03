package day1

import support.downloadInput
import support.toInts1

fun main() {

    println(solve(downloadInput(1)))

}

fun solve(input: List<String>): Any {
    return input.toInts1().windowed(4).fold(0) { acc, depths ->
        acc + if (depths[0] < depths[3]) 1 else 0
    }
}
