package support


public fun List<String>.toInts1(): List<Int> {
    return map { it.toInt() }
}

public fun List<String>.toInts2(separator: Char = ','): List<Pair<Int, Int>> {
    return map {
        val values = it.split(separator).map {
            it.toInt()
        }
        Pair(values[0], values[1])
    }
}

fun <T> List<T>.splitOn(cutArg: (T) -> Boolean): List<List<T>> {
    var result = mutableListOf<List<T>>()
    var curr = mutableListOf<T>()
    forEach {
        if (cutArg(it)) {
            result.add(curr)
            curr = mutableListOf()
        } else {
            curr.add(it)
        }
    }
    if (!curr.isEmpty()) {
        result.add(curr)
    }
    return result
}
