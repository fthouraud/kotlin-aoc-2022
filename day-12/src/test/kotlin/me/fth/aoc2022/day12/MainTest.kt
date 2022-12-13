package me.fth.aoc2022.day12

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  context("findShortestDistance") {
    should("find a distance of 31") {
      withInput(javaClass.getResource("/heightmap.txt")) {
        runBlocking { findShortestDistance() shouldBe 31 }
      }
    }
  }
  context("findShortestDistanceFromAnyLowestPoint") {
    should("find a distance of 29") {
      withInput(javaClass.getResource("/heightmap.txt")) {
        findShortestDistanceFromAnyLowestPoint() shouldBe 29
      }
    }
  }
})
