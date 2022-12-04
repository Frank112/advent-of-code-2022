package day03

data class Rucksack(val compartment1: List<Item> , val compartment2: List<Item>) {

    val itemSet: Set<Item> = setOf(elements = (compartment1 + compartment2).toTypedArray())

    constructor(line: String) : this(listOf(elements = line.substring(0, line.length / 2).toCharArray().map { c -> Item(c) }.toTypedArray()),
        listOf(elements = line.substring(line.length / 2).toCharArray().map { c -> Item(c) }.toTypedArray())) {
    }

    fun findItemInBothCompartments(): Item {
        return compartment1.intersect(compartment2.toSet()).first()
    }

    fun findItemsInBoth(other: Rucksack): Set<Item> {
        return itemSet.intersect(other.itemSet)
    }

    companion object {
        fun findBadge(rucksack1: Rucksack, rucksack2: Rucksack, rucksack3: Rucksack): Item {
            return rucksack1.findItemsInBoth(rucksack2).intersect(rucksack3.itemSet).first()
        }
    }

}

data class Item(val type: Char) {

    val value: Int = type.code - if (type.isLowerCase()) 96 else 38
}
