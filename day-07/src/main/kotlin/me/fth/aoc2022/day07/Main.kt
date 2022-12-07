package me.fth.aoc2022.day07

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/terminal-output.txt")

private const val DIRECTORY_SIZE_LIMIT = 100000L

fun main() = withInput(input) {
  printMeasuredTime {
    println("Sum of directory's size under $DIRECTORY_SIZE_LIMIT: ${sumOfFilesUnderSizeLimit()}")
    println("Smallest directory's size to free: ${findSmallestDirectorySize()}")
  }
}

context(InputContext)
fun sumOfFilesUnderSizeLimit(directory: Directory = parseDirectories(), sizeToFree: Long = 0): Long {
  val newSizeToFree = if (directory.size <= DIRECTORY_SIZE_LIMIT) directory.size + sizeToFree else sizeToFree
  return if (directory.subDirectories.isEmpty()) {
    newSizeToFree
  } else {
    directory.subDirectories.sumOf { sumOfFilesUnderSizeLimit(it, newSizeToFree) }
  }
}

private const val MAX_FILESYSTEM_SIZE = 70000000L
private const val MIN_REQUIRED_SIZE = 30000000L

context(InputContext)
fun findSmallestDirectorySize(): Long {
  val rootDirectory = parseDirectories()
  val minimumSizeToFree = MIN_REQUIRED_SIZE - (MAX_FILESYSTEM_SIZE - rootDirectory.size)
  return findSmallestDirectory(rootDirectory, minimumSizeToFree)
}

fun findSmallestDirectory(directory: Directory, spaceToFree: Long, closestDirectorySize: Long = MIN_REQUIRED_SIZE): Long {
  val directorySize = directory.size.takeIf { it in spaceToFree until closestDirectorySize } ?: closestDirectorySize
  return if (directory.subDirectories.isEmpty()) {
    directorySize
  } else {
    directory.subDirectories.minOf { findSmallestDirectory(it, spaceToFree, directorySize) }
  }
}

val numbers = '0'..'9'

context(InputContext)
fun parseDirectories(): Directory = Directory("/").also { root ->
  lineSequence().drop(2).fold(root) { directory, line ->
    when {
      line.endsWith(" ..") -> directory.parent ?: directory
      line.startsWith("$ cd") -> Directory(line.substring(5), parent = directory)
      line.first() in numbers -> directory.also { directory.fileSize += line.substring(0, line.indexOf(' ')).toLong() }
      else -> directory
    }
  }
}
