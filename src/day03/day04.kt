package day03

import assertThat
import readInput

fun main() {

    fun parseInput(input: List<String>): List<Rucksack> {
        return input.map { l -> Rucksack(l) }
    }

    fun part1(input: List<Rucksack>): Int {
        return input.map { r -> r.findItemInBothCompartments() }
            .sumOf { i -> i.value }
    }

    fun part2(input: List<Rucksack>): Int {
        return input.chunked(3)
            .map { r -> Rucksack.findBadge(r[0], r[1], r[2]) }
            .sumOf { i -> i.value }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day03/Day03_test"))
    assertThat(part1(testInput), 157)
    assertThat(part2(testInput), 70)

    val input = parseInput(readInput("day03/Day03"))
    println(part1(input))
    println(part2(input))
}