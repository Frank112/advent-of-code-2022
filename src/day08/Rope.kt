package day08

import kotlin.math.abs

data class Rope(val length: Int) {
    private var knots: MutableList<Position> = (0 until length).map { Position(0, 0) }.toMutableList()

    var tailPositionHistory: Set<Position> = setOf(knots.last())

    fun handle(command: MoveCommand) {
        (1..command.count).forEach { _ ->
            knots[0] = knots[0].move(command.direction)
            //println("Moved head to ${knots[0]}")
            for (i in knots.indices.drop(1)) {
                val previousKnot = knots[i - 1]
                if (!previousKnot.isConnected(knots[i])) {
                    knots[i] = moveKnotToStayConnected(previousKnot, knots[i])
                    //println("Moved knot $i to ${knots[i]}")
                }
            }
            tailPositionHistory += knots.last()
        }
    }

    private fun moveKnotToStayConnected(previousKnot: Position, knot: Position): Position {
        val diffX = knot.x - previousKnot.x
        val diffY = knot.y - previousKnot.y
        val diffRowAndCollumn = knot.x != previousKnot.x && knot.y != previousKnot.y
        var newKnot: Position = knot
        if (abs(diffX) > 1 || diffRowAndCollumn) {
            newKnot = if (diffX > 0) newKnot.moveLeft() else newKnot.moveRight()
        }
        if (abs(diffY) > 1 || diffRowAndCollumn) {
            newKnot = if (diffY > 0) newKnot.moveDown() else newKnot.moveUp()
        }
        return newKnot
    }

}

enum class Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT
}

data class MoveCommand(val direction: Direction, val count: Int)

data class Position(val x: Int, val y: Int) {

    fun move(direction: Direction): Position {
        return when (direction) {
            Direction.UP -> moveUp()
            Direction.DOWN -> moveDown()
            Direction.RIGHT -> moveRight()
            Direction.LEFT -> moveLeft()
        }
    }

    fun moveUp(): Position {
        return Position(x, y + 1)
    }

    fun moveDown(): Position {
        return Position(x, y - 1)
    }

    fun moveRight(): Position {
        return Position(x + 1, y)
    }

    fun moveLeft(): Position {
        return Position(x - 1, y)
    }

    fun isConnected(other: Position): Boolean {
        return abs(other.x - x) <= 1 && abs(other.y - y) <= 1
    }
}
