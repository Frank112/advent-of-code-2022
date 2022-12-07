package day05

data class Stack(val id: Int, var crates: List<String>) {

    fun top(): String {
        return crates.last()
    }

    fun add(values: List<String>) {
        crates += values
    }

    fun addBottom(value: String) {
        crates = listOf(value) + crates
    }

    fun withdraw(amount: Int): List<String> {
        val elements = crates.subList(crates.size - amount, crates.size)
        crates = crates.dropLast(amount)
        return elements.reversed()
    }

    fun withdraw9001(amount: Int): List<String> {
        val elements = crates.subList(crates.size - amount, crates.size)
        crates = crates.dropLast(amount)
        return elements
    }
}

data class Stacks(val stacks: Map<Int, Stack>) {

    fun handle(command: MoveCratesCommand, withdrawFunction: (Stack, Int) -> List<String>) {
        val fromStack = stacks[command.fromStackId]!!
        val toStack = stacks[command.toStackId]!!
        val elements = withdrawFunction.invoke(fromStack, command.amount)
        toStack.add(elements)
    }

    companion object {
        fun of(stacks: List<Stack>): Stacks {
            return Stacks(stacks.associateBy { it.id })
        }
    }
}


data class MoveCratesCommand(val amount: Int, val fromStackId: Int, val toStackId: Int)