package me.fth.aoc2022.day02

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/strategy-guide.txt")

  context("getMyScoreWithoutStrategy") {
    should("get a score of 15") {
      withInput(input) {
        getMyScoreWithoutStrategy() shouldBe 15
      }
    }
  }
  context("getMyScoreWithStrategy") {
    should("get a score of 12") {
      withInput(input) {
        getMyScoreWithStrategy() shouldBe 12
      }
    }
  }
})
