package day07

import assertThat
import readInput

fun main() {

    val cdCommandRegex = Regex("^\\$ cd (.+)$")
    val lsCommandRegex = Regex("^\\$ ls$")
    val directoryListingRegex = Regex("^dir (.+)$")
    val fileListingRegex = Regex("^(\\d+) (.+)$")

    fun parseInput(input: List<String>): Directory {
        val rootDir = Directory(cdCommandRegex.matchEntire(input[0])!!.groupValues[1], listOf())
        var currentDir = rootDir
        for (line in input.drop(1)) {
            if (line.matches(lsCommandRegex)) {
                continue
            } else if (line.matches(cdCommandRegex)) {
                val dirName = cdCommandRegex.matchEntire(line)!!.groupValues[1]
                currentDir = if (dirName == "..") currentDir.parentDir!! else currentDir.findDirectoryByName(dirName)!!
            } else if (line.matches(directoryListingRegex)) {
                val dirName = directoryListingRegex.matchEntire(line)!!.groupValues[1]
                currentDir.add(Directory(dirName, listOf(), currentDir))
            } else if (line.matches(fileListingRegex)) {
                val fileListingValues = fileListingRegex.matchEntire(line)!!.groupValues
                currentDir.add(File(fileListingValues[2], fileListingValues[1].toLong()))
            } else {
                throw IllegalStateException("Could not process line: '$line'")
            }
        }
        return rootDir
    }

    fun part1(input: Directory): Long {
        return Directory.findAllDirectoriesWithMaximumSize(input, 100000).sumOf { it.size() }
    }

    fun part2(input: Directory): Long {
        val requiredSpace = 30000000 - (70000000 - input.size())
        return input.findAllDirectoriesWithMinimumSize(requiredSpace).sortedBy { it.size() }[0].size()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day07/Day07_test"))
    println("Parsed test input: ${testInput}")
    assertThat(part1(testInput), 95437)
    assertThat(part2(testInput), 24933642)

    val input = parseInput(readInput("day07/Day07"))
    println(part1(input))
    println(part2(input))
}