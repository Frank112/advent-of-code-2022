package day10

data class Cpu(val commands: List<Command>, var registerX: Int = 1) {
    private var commandInExecutionIndex = 0
    private var commandCycles = 0

    fun tick() {
        val commandInExecution = commands[commandInExecutionIndex]

        commandCycles++

        if(commandInExecution.requiredCycles == commandCycles) {

            execute(commandInExecution)

            commandInExecutionIndex++
            commandCycles = 0
        }
    }

    fun finished(): Boolean {
        return commandInExecutionIndex >= commands.size
    }

    private fun execute(command: Command) {
        when (command) {
            is AddXCommand -> registerX += command.value
        }
    }

}

interface Command {
    val requiredCycles: Int
}

class NoopCommand() : Command {
    override val requiredCycles: Int
        get() = 1
}

data class AddXCommand(val value: Int) : Command {
    override val requiredCycles: Int
        get() = 2
}