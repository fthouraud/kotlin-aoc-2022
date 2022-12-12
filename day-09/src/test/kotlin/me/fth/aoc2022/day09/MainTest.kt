package me.fth.aoc2022.day09

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  context("countPositionsVisitedByRopeTail") {
    should("find 13 positions") {
      withInput(javaClass.getResource("/motions.txt")) {
        countPositionsVisitedByRopeTail().size shouldBe 13
      }
    }
  }
  context("countPositionsVisitedByLooseRopeTail") {
    should("find a scenic score of 1") {
      withInput(javaClass.getResource("/motions.txt")) {
        countPositionsVisitedByLooseRopeTail() shouldBe 1
      }
    }
    should("find a scenic score of 36") {
      withInput(javaClass.getResource("/motions-2.txt")) {
        countPositionsVisitedByLooseRopeTail() shouldBe 36
      }
    }
  }
})
