package me.fth.aoc2022.day07

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/terminal-output.txt")

  context("sumOfFilesUnderSizeLimit") {
    should("find 95437 as the sum of directory's sizes under 100 000") {
      withInput(input) {
        sumOfFilesUnderSizeLimit() shouldBe 95437
      }
    }
  }
  context("findSmallestDirectorySize") {
    should("find 24 933 642 as the smallest directory's side freeing enough space") {
      withInput(input) {
        findSmallestDirectorySize() shouldBe 24933642
      }
    }
  }
})
