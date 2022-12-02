package day02

enum class Shape(val value: Int) {
    Rock(1),
    Paper(2),
    Scissor(3);

    fun fight(other: Shape): Result {
        return when(this) {
            Scissor -> when(other) {
                Rock -> Result.LOST
                Paper -> Result.WON
                Scissor -> Result.DRAW
            }
            Rock -> when(other) {
                Rock -> Result.DRAW
                Paper -> Result.LOST
                Scissor -> Result.WON
            }
            Paper -> when(other) {
                Rock -> Result.WON
                Paper -> Result.DRAW
                Scissor -> Result.LOST
            }
        }
    }

    fun selectShapeByResult(expectedResult: Result): Shape {
        return when(expectedResult) {
            Result.WON -> when(this) {
                Rock -> Paper
                Paper -> Scissor
                Scissor -> Rock
            }
            Result.DRAW -> this
            Result.LOST -> when(this) {
                Rock -> Scissor
                Paper -> Rock
                Scissor -> Paper
            }
        }
    }
 }

enum class Result(val value: Int) {
    WON(6),
    DRAW(3),
    LOST(0)
}