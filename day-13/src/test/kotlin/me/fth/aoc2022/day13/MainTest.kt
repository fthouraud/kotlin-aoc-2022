package me.fth.aoc2022.day13

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/distress-signal.txt")

  context("sumIndicesOfLeftPackagesInOrder") {
    should("find a sum of 13") {
      withInput(input) {
        sumIndicesOfLeftPackagesInOrder() shouldBe 13
      }
    }
  }
  context("findDecoderKey") {
    should("find a key of 140") {
      withInput(input) {
        findDecoderKey() shouldBe 140
      }
    }
  }
})
