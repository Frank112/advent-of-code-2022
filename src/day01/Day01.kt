package day01

import readInput

fun main() {

    fun computeCarriedFoodByElf(input: List<String>): List<Int> {
        val carriedFoodByElfs = mutableListOf<Int>()
        var carriedFoodByElf = 0
        for (s in input) {
            if(s.isBlank()) {
                carriedFoodByElfs.add(carriedFoodByElf)
                carriedFoodByElf = 0
            } else {
                carriedFoodByElf += s.toInt()
            }
        }
        carriedFoodByElfs.add(carriedFoodByElf)
        return carriedFoodByElfs
    }

    fun part1(carriedFoodByElfs: List<Int>): Int {
        return carriedFoodByElfs.max()
    }

    fun part2(carriedFoodByElfs: List<Int>): Int {
        return carriedFoodByElfs.sortedDescending().subList(0, 3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = computeCarriedFoodByElf(readInput("day01/Day01_test"))
    val testResult1 = part1(testInput)
    val testResult2 = part2(testInput)
    check(testResult1 == 24000) { "Got ${testResult1} but expected 24000" }
    check(testResult2 == 45000) { "Got ${testResult2} but expected 45000" }

    val input = computeCarriedFoodByElf(readInput("day01/Day01"))
    println(part1(input))
    println(part2(input))
}
