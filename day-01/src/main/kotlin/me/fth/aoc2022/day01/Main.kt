package me.fth.aoc2022.day01

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/elves-calories.txt")

fun main() = withInput(input) {
  println("Greatest calories held: ${getGreatestCaloriesAmount()}")
  println("Sum of top 3 greatest calories held: ${getTop3CaloriesAmount().sum()}")
}

context(InputContext)
fun getGreatestCaloriesAmount() = getCaloriesByElves().max()

context(InputContext)
fun getTop3CaloriesAmount() = getCaloriesByElves().sortedDescending().take(3)

context(InputContext)
fun getCaloriesByElves(): List<Int> = input.readText().lines()
  .fold(mutableListOf(mutableListOf<Int>())) { values, input ->
    if (input.isBlank()) {
      values += mutableListOf<Int>()
    } else {
      values[values.lastIndex] += input.toInt()
    }
    values
  }.map { it.sum() }
