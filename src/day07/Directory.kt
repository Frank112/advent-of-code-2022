package day07

interface FileSystemObject {

    fun size(): Long
}

data class Directory(val name: String, var content: List<FileSystemObject>, val parentDir: Directory? = null) :
    FileSystemObject {

    fun add(obj: FileSystemObject) {
        content += obj
    }

    fun findDirectoryByName(name: String): Directory? {
        return content.filterIsInstance(Directory::class.java).find { it.name == name }
    }

    fun findAllDirectoriesWithMaximumSize(size: Long): List<Directory> {
        val directories = content.filterIsInstance(Directory::class.java)
        return directories.filter { it.size() < size } + directories.flatMap { it.findAllDirectoriesWithMaximumSize(size) }
    }

    fun findAllDirectoriesWithMinimumSize(size: Long): List<Directory> {
        val directories = content.filterIsInstance(Directory::class.java)
        return directories.filter { it.size() > size } + directories.flatMap { it.findAllDirectoriesWithMinimumSize(size) }
    }

    override fun size(): Long {
        return content.sumOf { it.size() }
    }

    override fun toString(): String {
        return "Directory(name='$name', content=$content, parentDir=${parentDir?.name})"
    }

    companion object {

        fun findAllDirectoriesWithMaximumSize(directory: Directory, size: Long): List<Directory> {
            val directories = directory.findAllDirectoriesWithMaximumSize(size)
            return if (directory.size() < size) directories + directory else directories
        }
    }

}

data class File(val name: String, val size: Long) : FileSystemObject {
    override fun size(): Long {
        return size
    }
}
