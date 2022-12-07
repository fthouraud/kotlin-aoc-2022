package me.fth.aoc2022.day07

data class Directory(val name: String, val parent: Directory? = null) {
  var fileSize: Long = 0
  val subDirectories: MutableSet<Directory> = mutableSetOf()
  val size: Long
    get() = subDirectories.sumOf { it.size } + fileSize

  init {
    parent?.subDirectories?.add(this)
  }
}
