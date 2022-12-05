package me.fth.aoc2022.day05

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/crates-instructions.txt")

  context("sequentiallyMoveCratesThenListTopOnes") {
    should("find CMZ as top crates") {
      withInput(input) {
        sequentiallyMoveCratesThenListTopOnes() shouldBe "CMZ"
      }
    }
  }
  context("simultaneouslyMoveCratesThenListTopOnes") {
    should("find MCD as top crates") {
      withInput(input) {
        simultaneouslyMoveCratesThenListTopOnes() shouldBe "MCD"
      }
    }
  }
})
