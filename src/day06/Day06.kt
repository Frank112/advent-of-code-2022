package day06

import assertThat
import readInput

fun main() {

    fun parseInput(input: List<String>): String {
        return input[0]
    }

    fun detectMarker(input: String, length: Int): Int {
        for(i in input.indices) {
            if (input.substring(i, i+length).toSet().size == length) {
                return i + length
            }
        }
        throw IllegalStateException("Could not calculate marker with length $length for input '$input'")
    }

    fun part1(input: String): Int {
        return detectMarker(input, 4)
    }

    fun part2(input: String): Int {
        return detectMarker(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day06/Day06_test"))
    println("Parsed test input: $testInput")
    assertThat(part1(testInput), 7)
    assertThat(part1("bvwbjplbgvbhsrlpgdmjqwftvncz"), 5)
    assertThat(part1("nppdvjthqldpwncqszvftbrmjlhg"), 6)
    assertThat(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), 10)
    assertThat(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), 11)
    assertThat(part2(testInput), 19)
    assertThat(part2("bvwbjplbgvbhsrlpgdmjqwftvncz"), 23)
    assertThat(part2("nppdvjthqldpwncqszvftbrmjlhg"), 23)
    assertThat(part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), 29)
    assertThat(part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), 26)

    val input = parseInput(readInput("day06/Day06"))
    println(part1(input))
    println(part2(input))
}