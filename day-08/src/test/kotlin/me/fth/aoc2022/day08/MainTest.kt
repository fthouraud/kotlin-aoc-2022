package me.fth.aoc2022.day08

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  val input = javaClass.getResource("/trees.txt")

  context("countVisibleTreesFromTheEdges") {
    should("see 21 trees from the edges") {
      withInput(input) {
        countVisibleTreesFromTheEdges() shouldBe 21
      }
    }
  }
  context("findGreatestScenicScore") {
    should("find 8 as the greatest scenic score") {
      withInput(input) {
        findGreatestScenicScore() shouldBe 8
      }
    }
  }
})
