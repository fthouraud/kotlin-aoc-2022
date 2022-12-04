package me.fth.aoc2022.day04

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/elves-assignments.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("How many assignations fully overlap: ${countFullyOverlappingAssignations()}")
    println("How many assignations partially overlap: ${countPartiallyOverlappingAssignations()}")
  }
}

context(InputContext)
fun countFullyOverlappingAssignations() = lineSequence().count {
  it.toAssignmentSets().let { (assignationA, assignationB) ->
    if (assignationA.size >= assignationB.size) {
      assignationA.containsAll(assignationB)
    } else {
      assignationB.containsAll(assignationA)
    }
  }
}

context(InputContext)
fun countPartiallyOverlappingAssignations() = lineSequence().count {
  it.toAssignmentSets().let { (assignationA, assignationB) -> assignationA.intersect(assignationB).isNotEmpty() }
}

fun String.toAssignmentSets() = split("[-,]".toRegex()).let { (firstRangeStart, firstRangeStop, secondRangeStart, secondRangeStop) ->
  (firstRangeStart.toInt()..firstRangeStop.toInt()).toSet() to (secondRangeStart.toInt()..secondRangeStop.toInt()).toSet()
}
