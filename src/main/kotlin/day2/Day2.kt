package day2

import support.downloadInput

fun main() {

    println(solve(downloadInput(2)))

}

fun solve(input: List<String>): Any {
    var forward = 0
    var depth = 0
    var aim = 0
    input.forEach { line ->
        val (action, rawNum) = line.split(' ')
        val num = rawNum.toInt()
        when (action) {
            "forward" -> {
                forward += num
                depth += num * aim
            }
            "up" -> aim -= num
            "down" -> aim += num
        }
    }

    return depth * forward
}
