package me.fth.aoc2022.day03

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/rucksacks-content.txt")

  context("getSumOfPrioritiesPerRucksacks") {
    should("get a total priority of 157") {
      withInput(input) {
        getSumOfPrioritiesPerRucksacks() shouldBe 157
      }
    }
  }
  context("getSumOfPrioritiesPerRucksackGroups") {
    should("get a total priority of 70") {
      withInput(input) {
        getSumOfPrioritiesPerRucksackGroups() shouldBe 70
      }
    }
  }
})
