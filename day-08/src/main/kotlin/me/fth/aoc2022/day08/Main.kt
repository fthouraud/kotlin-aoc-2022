package me.fth.aoc2022.day08

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/trees.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Number of visible trees: ${countVisibleTreesFromTheEdges()}")
    println("Greatest scenic score: ${findGreatestScenicScore()}")
  }
}

context(InputContext)
fun countVisibleTreesFromTheEdges() = lineSequence().fold(mutableListOf<List<Short>>()) { acc, row ->
  acc.also { it.add(row.map { c -> c.digitToInt().toShort() }) }
}.let {
  var visibleTrees = 0
  (1 until it.lastIndex).forEach { x ->
    (1 until it[x].lastIndex).forEach { y ->
      val value = it[x][y]
      val isVisibleFromEdges = ((y - 1) downTo 0).all { yy -> it[x][yy] < value } ||
        ((y + 1)..it[x].lastIndex).all { yy -> it[x][yy] < value } ||
        ((x - 1) downTo 0).all { xx -> it[xx][y] < value } ||
        ((x + 1)..it.lastIndex).all { xx -> it[xx][y] < value }
      if (isVisibleFromEdges) visibleTrees++
    }
  }
  visibleTrees + ((it.size + it.first().size) * 2) - 4
}

context(InputContext)
fun findGreatestScenicScore() = lineSequence().fold(mutableListOf<List<Short>>()) { acc, row ->
  acc.also { it.add(row.map { c -> c.digitToInt().toShort() }) }
}.let {
  val scenicScores = mutableSetOf<Int>()
  (1 until it.lastIndex).forEach { x ->
    (1 until it[x].lastIndex).forEach { y ->
      val value = it[x][y]
      val top = y - (((y - 1) downTo 0).firstOrNull { yy -> it[x][yy] >= value } ?: 0)
      val bottom = (((y + 1)..it[x].lastIndex).firstOrNull { yy -> it[x][yy] >= value } ?: it[x].lastIndex) - y
      val left = x - (((x - 1) downTo 0).firstOrNull { xx -> it[xx][y] >= value } ?: 0)
      val right = (((x + 1)..it.lastIndex).firstOrNull { xx -> it[xx][y] >= value } ?: it.lastIndex) - x
      println("${it[x][y]} ($x, $y) ($top, $bottom, $left, $right)")
      scenicScores.add(top * bottom * left * right)
    }
  }
  scenicScores.max()
}
