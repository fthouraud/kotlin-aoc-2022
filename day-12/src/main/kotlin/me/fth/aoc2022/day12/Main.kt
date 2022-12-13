package me.fth.aoc2022.day12

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL
import java.util.ArrayDeque
import kotlin.math.max
import kotlin.math.min

val input: URL = object {}.javaClass.getResource("/heightmap.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Shortest path from starting point: ${findShortestDistance()}")
    println("Shortest path from any lowest point: ${findShortestDistanceFromAnyLowestPoint()}")
  }
}

typealias HeightMap = List<CharArray>
typealias Coordinate = Pair<Int, Int>
typealias Step = Pair<Coordinate, Int>

context(InputContext)
fun findShortestDistance(): Int = lineSequence().map { it.toCharArray() }.toList().let { heightmap ->
  val startingPoint = heightmap.findFirstCoordinateOrNull('S')!!.also { (x, y) -> heightmap[y][x] = 'a' }
  val destinationPoint = heightmap.findFirstCoordinateOrNull('E')!!.also { (x, y) -> heightmap[y][x] = 'z' }
  heightmap.findShortestPath(destinationPoint, startingPoint)
}

fun HeightMap.findFirstCoordinateOrNull(value: Char): Coordinate? {
  for ((y, row) in withIndex()) {
    val x = row.indexOfFirst { it == value }
    if (x != -1) return x to y
  }
  return null
}

fun HeightMap.findShortestPath(from: Coordinate, to: Coordinate): Int {
  val steps = ArrayDeque<Step>().also { it.add(Step(from, 0)) }
  val visited = mutableSetOf<Coordinate>()

  while (steps.isNotEmpty()) {
    val (coordinate, distance) = steps.pop()
    for (neighbor in findAccessibleCoordinatesFrom(coordinate).filter { it !in visited }) {
      if (neighbor == to) return distance + 1
      visited.add(neighbor)
      steps.add(neighbor to distance + 1)
    }
  }

  return Int.MAX_VALUE
}

fun HeightMap.findAccessibleCoordinatesFrom(coordinate: Coordinate): Set<Coordinate> = coordinate.let { (x, y) ->
  return listOf(
    x to max(y - 1, 0),
    x to min(y + 1, lastIndex),
    max(x - 1, 0) to y,
    min(x + 1, this[0].lastIndex) to y,
  ).filter { (xx, yy) -> (xx != x || yy != y) && this[yy][xx] >= this[y][x] - 1 }.toSet()
}

context(InputContext)
fun findShortestDistanceFromAnyLowestPoint(): Int = lineSequence().map { it.toCharArray() }.toList().let { heightmap ->
  val destinationPoint = heightmap.findFirstCoordinateOrNull('E')!!.also { (x, y) -> heightmap[y][x] = 'z' }
  heightmap.flatMapIndexed { colIndex, row ->
    row.mapIndexed { rowIndex, c ->
      if (c == 'a') rowIndex to colIndex
      else null
    }.filterNotNull()
  }.map { heightmap.findShortestPath(destinationPoint, it) }.min()
}
