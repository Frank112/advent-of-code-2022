package day10

class Crt(val cpu: Cpu) {

    var cycle = 0
    var screen: String = ""

    fun tick() {

        drawPixel()

        if(position() == 39) {
            screen += "\n"
        }

        cpu.tick()
        cycle++
    }

    fun finished(): Boolean {
        return cpu.finished()
    }

    private fun drawPixel() {
        screen += if(isCurrentPositionDark()) "." else "#"
    }

    private fun isCurrentPositionDark(): Boolean {
        return position() > cpu.registerX + 1 || position() < cpu.registerX - 1
    }

    private fun position(): Int {
        return cycle % 40
    }
}