import day08.Direction
import day08.MoveCommand
import day08.Rope

fun main() {

    val moveCommandRegex = Regex("^([DULR]) (\\d+)$")

    fun mapDirection(s: String): Direction {
        return when(s) {
            "D" -> Direction.DOWN
            "U" -> Direction.UP
            "L" -> Direction.LEFT
            "R" -> Direction.RIGHT
            else -> { throw IllegalArgumentException("Could not map direction '$s'")}
        }
    }

    fun parseInput(input: List<String>): List<MoveCommand> {
        return input.map { moveCommandRegex.matchEntire(it)!! }
            .map { MoveCommand(mapDirection(it.groupValues[1]), it.groupValues[2].toInt()) }
    }

    fun part1(input: List<MoveCommand>): Int {
        val rope = Rope(2)
        input.forEach { rope.handle(it) }
        return rope.tailPositionHistory.toSet().size
    }

    fun part2(input: List<MoveCommand>): Int {
        val rope = Rope(10)
        input.forEach { rope.handle(it) }
        return rope.tailPositionHistory.toSet().size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day09/Day09_test"))
    println("Parsed test input: $testInput")
    assertThat(part1(testInput), 13)
    assertThat(part2(testInput), 1)
    assertThat(part2(parseInput(readInput("day09/Day09_test2"))), 36)

    val input = parseInput(readInput("day09/Day09"))
    println(part1(input))
    println(part2(input))
}