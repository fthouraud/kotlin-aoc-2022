package me.fth.aoc2022.day05

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL
import java.util.LinkedList

val input: URL = object {}.javaClass.getResource("/crates-instructions.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Top crates (moved sequentially): ${sequentiallyMoveCratesThenListTopOnes()}")
    println("Top crates (moved simultaneously): ${simultaneouslyMoveCratesThenListTopOnes()}")
  }
}

context(InputContext)
fun sequentiallyMoveCratesThenListTopOnes() = moveCratesThenListTopOnes { fromStack, toStack, numberOfCrates ->
  repeat(numberOfCrates) { toStack.addFirst(fromStack.poll()) }
}

context(InputContext)
fun simultaneouslyMoveCratesThenListTopOnes() = moveCratesThenListTopOnes { fromStack, toStack, numberOfCrates ->
  (1..numberOfCrates).map { fromStack.poll() }.reversed().forEach { toStack.addFirst(it) }
}

context(InputContext)
fun moveCratesThenListTopOnes(moveCrates: (LinkedList<Char>, LinkedList<Char>, Int) -> Unit) =
  loadCratesLocations().also { stacks ->
    loadingInstructions().forEach {
      it.parseInstruction().also { (numberOfCrates, from, to) ->
        moveCrates(stacks[from - 1], stacks[to - 1], numberOfCrates)
      }
    }
  }.map { it.element() }.joinToString("")

private const val SIZE_BETWEEN_CRATE_LETTER = 4
val crateLetters = 'A'..'Z'

context(InputContext)
fun loadCratesLocations(): List<LinkedList<Char>> {
  return lineSequence().takeWhile { it.isNotBlank() }.fold(mutableMapOf<Int, LinkedList<Char>>()) { stacks, line ->
    stacks.also {
      (1..line.lastIndex step SIZE_BETWEEN_CRATE_LETTER).forEachIndexed { stackIndex, crateIndex ->
        line[crateIndex].takeIf { it in crateLetters }
          ?.let { stacks.getOrPut(stackIndex) { LinkedList() }.add(it) }
      }
    }
  }.toSortedMap().values.toList()
}

context(InputContext)
fun loadingInstructions() = lineSequence().dropWhile { it.isNotBlank() }.drop(1)

private val numberRegex = """(\d+)""".toRegex()

fun String.parseInstruction(): Triple<Int, Int, Int> {
  val (number, from, to) = numberRegex.findAll(this).map { it.value.toInt() }.toList()
  return Triple(number, from, to)
}
