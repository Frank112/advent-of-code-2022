package day04

import assertThat
import readInput

fun main() {

    val inputParseRegex = Regex("^(\\d+)-(\\d+),(\\d+)-(\\d+)$")

    fun parseInput(input: List<String>): List<Pair<CleaningAssignment, CleaningAssignment>> {
        return input.map { l -> inputParseRegex.matchEntire(l)!!.groupValues.drop(1).map { s -> s.toInt() } }
            .map { sectionIds -> Pair(CleaningAssignment(sectionIds[0], sectionIds[1]), CleaningAssignment(sectionIds[2], sectionIds[3])) }
    }

    fun part1(input: List<Pair<CleaningAssignment, CleaningAssignment>>): Int {
        return input.count { p -> CleaningAssignment.isOneContainedByTheOther(p.first, p.second) }
    }

    fun part2(input: List<Pair<CleaningAssignment, CleaningAssignment>>): Int {
        return input.count{ p -> p.first.overlaps(p.second)}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day04/Day04_test"))
    println("Parsed test input: $testInput")
    assertThat(part1(testInput), 2)
    assertThat(part2(testInput), 4)

    val input = parseInput(readInput("day04/Day04"))
    println(part1(input))
    println(part2(input))
}