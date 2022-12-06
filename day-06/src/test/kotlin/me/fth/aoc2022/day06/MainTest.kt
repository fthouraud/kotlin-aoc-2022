package me.fth.aoc2022.day06

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import me.fth.aoc2022.common.withInput

class MainTest : ShouldSpec({
  context("countCharactersBeforeMarker") {
    should("count 7 characters in signal 01") {
      withInput(javaClass.getResource("/signal-01.txt")) {
        countCharactersBeforePacketMarker() shouldBe 7
      }
    }
    should("count 5 characters in signal 02") {
      withInput(javaClass.getResource("/signal-02.txt")) {
        countCharactersBeforePacketMarker() shouldBe 5
      }
    }
    should("count 6 characters in signal 03") {
      withInput(javaClass.getResource("/signal-03.txt")) {
        countCharactersBeforePacketMarker() shouldBe 6
      }
    }
    should("count 10 characters in signal 04") {
      withInput(javaClass.getResource("/signal-04.txt")) {
        countCharactersBeforePacketMarker() shouldBe 10
      }
    }
    should("count 11 characters in signal 05") {
      withInput(javaClass.getResource("/signal-05.txt")) {
        countCharactersBeforePacketMarker() shouldBe 11
      }
    }
  }
  context("countCharactersBeforeMessageMarker") {
    should("count 19 characters in signal 01") {
      withInput(javaClass.getResource("/signal-01.txt")) {
        countCharactersBeforeMessageMarker() shouldBe 19
      }
    }
    should("count 23 characters in signal 02") {
      withInput(javaClass.getResource("/signal-02.txt")) {
        countCharactersBeforeMessageMarker() shouldBe 23
      }
    }
    should("count 23 characters in signal 03") {
      withInput(javaClass.getResource("/signal-03.txt")) {
        countCharactersBeforeMessageMarker() shouldBe 23
      }
    }
    should("count 29 characters in signal 04") {
      withInput(javaClass.getResource("/signal-04.txt")) {
        countCharactersBeforeMessageMarker() shouldBe 29
      }
    }
    should("count 26 characters in signal 05") {
      withInput(javaClass.getResource("/signal-05.txt")) {
        countCharactersBeforeMessageMarker() shouldBe 26
      }
    }
  }
})
