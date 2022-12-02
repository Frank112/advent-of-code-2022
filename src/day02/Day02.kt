package day02

import assertThat
import readInput

fun main() {

    fun parseInput(input: List<String>): List<Pair<String, String>> {
        return input.map { s -> Pair(s.substring(0, 1), s.substring(2)) }
    }

    fun <T> mapper(map: Map<String, T>): (s: String) -> T {
        return { s -> map[s] ?: throw IllegalArgumentException("Input could not be mapped: $s") }
    }

    val opponentShapeMapper =
        mapper(mapOf(Pair("A", Shape.Rock), Pair("B", Shape.Paper), Pair("C", Shape.Scissor)))

    fun part1(input: List<Pair<String, String>>): Int {
        val ownShapeMapper =
            mapper(mapOf(Pair("X", Shape.Rock), Pair("Y", Shape.Paper), Pair("Z", Shape.Scissor)))
        return input.map { p ->
            Pair(
                opponentShapeMapper.invoke(p.first),
                ownShapeMapper.invoke(p.second)
            )
        }
            .sumOf { p -> p.second.fight(p.first).value + p.second.value }
    }

    fun part2(input: List<Pair<String, String>>): Int {
        val resultMapper = mapper(mapOf(Pair("X", Result.LOST), Pair("Y", Result.DRAW), Pair("Z", Result.WON)))
        return input.map { p -> Pair(opponentShapeMapper(p.first), resultMapper.invoke(p.second)) }
            .map { p -> Pair(p.first, p.first.selectShapeByResult(p.second)) }
            .sumOf { p -> p.second.fight(p.first).value + p.second.value }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day02/Day02_test"))
    assertThat(part1(testInput), 15)
    assertThat(part2(testInput), 12)

    val input = parseInput(readInput("day02/Day02"))
    println(part1(input))
    println(part2(input))

}