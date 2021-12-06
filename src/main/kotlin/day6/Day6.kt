package day6

import support.downloadInput

fun main() {

    println(solve(downloadInput(6, 1)))
    println("--------------------------")
    println(solve(downloadInput(6)))

}

fun solve(input: List<String>): Any {
    val startFish = input.first().split(',').map { it.toInt() }

    val fishMap = (0..8).map { fishAge ->
        var fish = listOf(fishAge)
        repeat(16) {
            var new = 0
            fish = fish.map {
                if (it == 0) {
                    new++
                    6
                } else {
                    it - 1
                }
            } + List(new) { 8 }
        }
        fish
    }
    var nextMap: List<Long> = fishMap.map { it.size.toLong() }
    repeat(15) {
        nextMap = (0..8).map { fishAge ->
            fishMap[fishAge].map { nextMap[it] }.sum()
        }
    }

    return startFish.map { nextMap[it] }.sum()
}
