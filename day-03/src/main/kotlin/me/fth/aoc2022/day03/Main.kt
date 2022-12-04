package me.fth.aoc2022.day03

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/rucksacks-content.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Rucksacks sum of content's priorities: ${getSumOfPrioritiesPerRucksacks()}")
    println("Rucksacks' groups sum of content's priorities: ${getSumOfPrioritiesPerRucksackGroups()}")
  }
}

context(InputContext)
fun getSumOfPrioritiesPerRucksacks() = lineSequence().map { rucksack ->
  rucksack.chunked(rucksack.length / 2).let { (firstCompartment, secondCompartment) ->
    firstCompartment.toSet().intersect(secondCompartment.toSet())
  }
}.sumOfPriorities()

private const val RUCKSACK_GROUP_SIZE = 3

context(InputContext)
fun getSumOfPrioritiesPerRucksackGroups() = lineSequence()
  .windowed(RUCKSACK_GROUP_SIZE, RUCKSACK_GROUP_SIZE) { (firstRucksack, secondRucksack, thirdRucksack) ->
    firstRucksack.toSet()
      .intersect(secondRucksack.toSet())
      .intersect(thirdRucksack.toSet())
  }.sumOfPriorities()

val itemsByPriority = (('a'..'z') + ('A'..'Z')).mapIndexed { index, c -> c to index + 1 }.toMap()

fun Sequence<Set<Char>>.sumOfPriorities() = sumOf { it.sumOf { item -> itemsByPriority[item]!! } }
