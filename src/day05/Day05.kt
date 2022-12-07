package day05

import assertThat
import readInput

fun main() {

    val crateLineRegex: Regex = Regex("(   |\\[(?<crate>[A-Z])\\]) ?")
    val moveCrateCommandLineRegex = Regex("^move (\\d+) from (\\d+) to (\\d+)$")

    fun <T> List<T>.splitBy(element: T): List<List<T>> {
        var list = this
        val listOfList = mutableListOf<List<T>>()
        do {
            val index = list.indexOf(element)
            if (index == -1) {
                listOfList.add(list)
            } else {
                listOfList.add(list.subList(0, index))
                list = list.subList(index + 1, list.size)
            }
        } while (index != -1)
        return listOfList
    }

    fun parseInput(input: List<String>): Pair<Stacks, List<MoveCratesCommand>> {
        val stacksAndCommands = input.splitBy("")
        val stacks = stacksAndCommands[0].last().split("   ").map { s -> Stack(s.trim().toInt(), listOf()) }
        stacksAndCommands[0].subList(0, stacksAndCommands[0].size - 1)
            .map { l -> crateLineRegex.findAll(l).map { m -> m.groups["crate"]?.value } }
            .forEach { s -> s.forEachIndexed { index, crate -> if (crate != null) stacks[index].addBottom(crate) }}

        val commands = stacksAndCommands[1].map { l -> moveCrateCommandLineRegex.matchEntire(l)!! }
            .map { m ->
                MoveCratesCommand(
                    m.groupValues[1].toInt(),
                    m.groupValues[2].toInt(),
                    m.groupValues[3].toInt()
                )
            }
        return Pair(Stacks.of(stacks), commands)
    }

    fun part1(input: Pair<Stacks, List<MoveCratesCommand>>): String {
        input.second.forEach{ input.first.handle(it, Stack::withdraw)}
        return input.first.stacks.values.map { it.top() }.joinToString(separator = "")
    }

    fun part2(input: Pair<Stacks, List<MoveCratesCommand>>): String {
        input.second.forEach{ input.first.handle(it, Stack::withdraw9001)}
        return input.first.stacks.values.map { it.top() }.joinToString(separator = "")    }


    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day05/Day05_test"))
    println("Parsed test input: $testInput")
    assertThat(part1(testInput), "CMZ")
    assertThat(part2(parseInput(readInput("day05/Day05_test"))), "MCD")

    println(part1(parseInput(readInput("day05/Day05"))))
    println(part2(parseInput(readInput("day05/Day05"))))
}