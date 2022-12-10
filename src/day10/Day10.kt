
import day10.*

fun main() {
    val commandRegex = Regex("^(noop|addx)( (-?\\d+))?\$")

    fun parseInput(input: List<String>): List<Command> {
        return input.map { commandRegex.matchEntire(it)!! }
            .map { when(it.groupValues[1]) {
                "noop" -> NoopCommand()
                "addx" -> AddXCommand(it.groupValues[3].toInt())
                else -> throw IllegalArgumentException("Unknown command: ${it.groupValues[0]}")
            } }
    }

    fun part1(input: List<Command>): Int {
        var signalStrength = 0
        val cpu = Cpu(input)
        (1..220).forEach { i ->
            if (i in setOf(20, 60, 100, 140, 180, 220)) {
                signalStrength += i * cpu.registerX
            }
            cpu.tick()
         }
        return signalStrength
    }

    fun part2(input: List<Command>): String {
        val crt = Crt(Cpu(input))
        while(!crt.finished()) {
            crt.tick()
        }
        return crt.screen
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("day10/Day10_test"))
    println("Parsed test input: $testInput")
    assertThat(part1(testInput), 13140)
    assertThat(part2(testInput), "##..##..##..##..##..##..##..##..##..##..\n" +
            "###...###...###...###...###...###...###.\n" +
            "####....####....####....####....####....\n" +
            "#####.....#####.....#####.....#####.....\n" +
            "######......######......######......####\n" +
            "#######.......#######.......#######.....\n")

    val input = parseInput(readInput("day10/Day10"))
    println(part1(input))
    println(part2(input))
}