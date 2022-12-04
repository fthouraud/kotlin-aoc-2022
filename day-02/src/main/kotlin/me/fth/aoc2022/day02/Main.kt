package me.fth.aoc2022.day02

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/strategy-guide.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("My score without strategy is: ${getMyScoreWithoutStrategy()}")
    println("My score with strategy is: ${getMyScoreWithStrategy()}")
  }
}

context (InputContext)
fun getMyScoreWithoutStrategy() = lineSequence()
  .map { play -> play.first().toMove() versus play.last().toMove() }
  .sumOf { it.second }

context (InputContext)
fun getMyScoreWithStrategy() = lineSequence()
  .map { play -> play.first().toMove().let { it versus it.expectedMove(play.last()) } }
  .sumOf { it.second }
