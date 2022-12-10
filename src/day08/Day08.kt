package day08

import assertThat
import readInput

fun main() {


    fun parseInput(input: List<String>): List<List<Int>> {
        return input.map { l -> l.chunked(1).map { it.toInt() } }
    }

    fun isTreeVisibleFromLeft(input: List<List<Int>>, x: Int, y: Int): Boolean {
        val treeHeight = input[x][y]
        for (left in 0 until x) {
            if (input[left][y] >= treeHeight) return false
        }
        return true
    }

    fun isTreeVisibleFromRight(input: List<List<Int>>, x: Int, y: Int): Boolean {
        val treeHeight = input[x][y]
        for (right in x + 1 until input[x].size) {
            if (input[right][y] >= treeHeight) return false
        }
        return true
    }

    fun isTreeVisibleFromTop(input: List<List<Int>>, x: Int, y: Int): Boolean {
        val treeHeight = input[x][y]
        for (top in 0 until y) {
            if (input[x][top] >= treeHeight) return false
        }
        return true
    }

    fun isTreeVisibleFromBottom(input: List<List<Int>>, x: Int, y: Int): Boolean {
        val treeHeight = input[x][y]
        for (bottom in y + 1 until input.size) {
            if (input[x][bottom] >= treeHeight) return false
        }
        return true
    }

    fun isTreeVisible(input: List<List<Int>>, x: Int, y: Int): Boolean {
        return isTreeVisibleFromLeft(input, x, y) || isTreeVisibleFromRight(input, x, y)
                || isTreeVisibleFromTop(input, x, y) || isTreeVisibleFromBottom(input, x, y)
    }

    fun countTreesInView(trees: List<Int>, treeHeight: Int): Int {
        var count = 0
        for(tree in trees) {
            count++
            if (tree >= treeHeight) return count
        }
        return count
    }

    fun viewDistance(rowOrColumn: List<Int>, treeIndex: Int): Pair<Int, Int> {
        val treeHeight = rowOrColumn[treeIndex]
        val treesLeft = rowOrColumn.subList(0, treeIndex).reversed()
        val treesRight = rowOrColumn.subList(treeIndex + 1, rowOrColumn.size)
        return Pair(countTreesInView(treesLeft, treeHeight), countTreesInView(treesRight, treeHeight))
    }

    fun computeScenicScore(input: List<List<Int>>, x: Int, y: Int): Int {
        val leftRightView = viewDistance(input[y], x)
        val topDownView = viewDistance(input.map { it[x] }, y)
        return leftRightView.first * leftRightView.second * topDownView.first * topDownView.second
    }

    fun part1(input: List<List<Int>>): Int {
        val length = input.size
        val height = input[0].size
        var countVisibleTrees = 0
        for (x in 1..length - 2) {
            for (y in 1..height - 2) {
                if (isTreeVisible(input, x, y)) countVisibleTrees++
            }
        }
        return length * 2 + (height - 2) * 2 + countVisibleTrees
    }

    fun part2(input: List<List<Int>>): Int {
        val length = input.size
        val height = input[0].size
        var maxScenicScore = Int.MIN_VALUE
        for (x in 1..length - 2) {
            for (y in 1..height - 2) {
                val currentScenicScore = computeScenicScore(input, x, y)
                if (currentScenicScore > maxScenicScore) maxScenicScore = currentScenicScore
            }
        }
        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day08/Day08_test"))
    println("Parsed test input: $testInput")
    assertThat(part1(testInput), 21)
    assertThat(part2(testInput), 8)

    val input = parseInput(readInput("day08/Day08"))
    println(part1(input))
    println(part2(input))
}