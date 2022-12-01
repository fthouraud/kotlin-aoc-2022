package me.fth.aoc2022.day01

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/elves-calories.txt")

  context("getGreatestCaloriesAmount") {
    should("get 7777 as the greatest amount of calories among the elves") {
      withInput(input) {
        getGreatestCaloriesAmount() shouldBe 7777
      }
    }
  }
  context("getTop3CaloriesAmount") {
    should("get the 3 greatest calories amount") {
      withInput(input) {
        getTop3CaloriesAmount() shouldContainInOrder listOf(7777, 1242, 126)
      }
    }
  }
})
