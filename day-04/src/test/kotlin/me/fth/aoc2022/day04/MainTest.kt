package me.fth.aoc2022.day04

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/elves-assignments.txt")

  context("countFullyOverlappingAssignations") {
    should("find 2 fully overlapping assignations") {
      withInput(input) {
        countFullyOverlappingAssignations() shouldBe 2
      }
    }
  }
  context("countPartiallyOverlappingAssignations") {
    should("get a total priority of 70") {
      withInput(input) {
        countPartiallyOverlappingAssignations() shouldBe 4
      }
    }
  }
})
