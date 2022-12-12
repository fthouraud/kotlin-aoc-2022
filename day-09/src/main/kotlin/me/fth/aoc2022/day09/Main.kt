package me.fth.aoc2022.day09

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/motions.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Number of positions the rope's tail visited: ${countPositionsVisitedByRopeTail().size}")
    println("Number of positions the rope's tail visited (loose): ${countPositionsVisitedByLooseRopeTail()}")
  }
}
typealias Coordinates = Pair<Int, Int>

val startingPoint = 0 to 0

context(InputContext)
fun countPositionsVisitedByRopeTail(): Set<Coordinates> {
  var currentHeadPosition = startingPoint
  var currentTailPosition = startingPoint
  return lineSequence().fold(mutableSetOf(startingPoint)) { visitedPositions, motion ->
    val (direction, steps) = motion.parse()
    repeat(steps) {
      val newCurrentHeadPosition = currentHeadPosition.move(direction)
      if (currentTailPosition isNotAdjacentTo newCurrentHeadPosition) {
        currentTailPosition = currentHeadPosition
        visitedPositions.add(currentTailPosition)
      }
      currentHeadPosition = newCurrentHeadPosition
    }
    visitedPositions
  }.toSet()
}

fun Coordinates.move(direction: Char) = when (direction) {
  'U' -> first to second + 1
  'D' -> first to second - 1
  'R' -> first + 1 to second
  'L' -> first - 1 to second
  else -> this
}

fun String.parse() = split(' ').let { it.first().first() to it.last().toInt() }

infix fun Coordinates.isNotAdjacentTo(coordinates: Coordinates): Boolean {
  val (x, y) = coordinates
  return first !in ((x - 1)..(x + 1)) || second !in ((y - 1)..(y + 1))
}

context(InputContext)
fun countPositionsVisitedByLooseRopeTail(): Int {
  return 0
}
