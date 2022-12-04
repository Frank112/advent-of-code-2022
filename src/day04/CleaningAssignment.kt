package day04

data class CleaningAssignment(val startSectionId: Int, val endSectionId: Int) {

    fun contains(other: CleaningAssignment): Boolean {
        return startSectionId <= other.startSectionId && endSectionId >= other.endSectionId
    }

    fun overlaps(other: CleaningAssignment): Boolean {
        return !(endSectionId < other.startSectionId || startSectionId > other.endSectionId)
    }

    fun hasLessSectionIds(other: CleaningAssignment): Boolean {
        return countSectionIds() < other.countSectionIds()
    }

    private fun countSectionIds(): Int {
        return endSectionId - startSectionId + 1
    }

    companion object {

        fun isOneContainedByTheOther(cleaningAssignment1: CleaningAssignment, cleaningAssignment2: CleaningAssignment): Boolean {
            if (cleaningAssignment1.hasLessSectionIds(cleaningAssignment2)) {
                return cleaningAssignment2.contains(cleaningAssignment1)
            } else {
                return cleaningAssignment1.contains(cleaningAssignment2)
            }
        }
    }
}
