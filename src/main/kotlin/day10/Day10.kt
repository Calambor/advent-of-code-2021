package day10

import support.downloadInput
import java.util.*

fun main() {

    println(solve2(downloadInput(10, 1)))
    println("--------------------------")
    println(solve2(downloadInput(10)))

}

val pairs = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>'
)

fun solve1(input: List<String>): Any {
    return input.map { line ->
        val openChunks = Stack<Char>()
        line.forEach { char ->
            when (char) {
                '(', '[', '{', '<' -> openChunks.add(char)
                ')', ']', '}', '>' -> {
                    if (!openChunks.isEmpty() && pairs[openChunks.peek()] == char) {
                        openChunks.pop()
                    } else {
                        return@map when (char) {
                            ')' -> 3
                            ']' -> 57
                            '}' -> 1197
                            '>' -> 25137
                            else -> throw IllegalStateException()
                        }
                    }
                }
            }
        }
        0
    }.sum()
}

fun solve2(input: List<String>): Any {
    val scores = input.mapNotNull { line ->
        val openChunks = Stack<Char>()
        line.forEach { char ->
            when (char) {
                '(', '[', '{', '<' -> openChunks.add(char)
                ')', ']', '}', '>' -> {
                    if (!openChunks.isEmpty() && pairs[openChunks.peek()] == char) {
                        openChunks.pop()
                    } else {
                        return@mapNotNull null
                    }
                }
            }
        }
        openChunks.reversed().fold(0L) { acc, char ->
            acc * 5 + when(char) {
                '(' -> 1
                '[' -> 2
                '{' -> 3
                '<' -> 4
                else -> throw IllegalStateException()
            }
        }
    }
    return scores.sorted()[scores.size/2]
}

