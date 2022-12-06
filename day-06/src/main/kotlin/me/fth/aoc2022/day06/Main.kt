package me.fth.aoc2022.day06

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/signal.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Characters before packet marker: ${countCharactersBeforePacketMarker()}")
    println("Characters before message marker: ${countCharactersBeforeMessageMarker()}")
  }
}

private const val PACKET_MARKER_SIZE = 4
private const val MESSAGE_MARKER_SIZE = 14

context(InputContext)
fun countCharactersBeforePacketMarker(): Int = countCharactersBeforeMarkerOfSize(PACKET_MARKER_SIZE)

context(InputContext)
fun countCharactersBeforeMessageMarker(): Int = countCharactersBeforeMarkerOfSize(MESSAGE_MARKER_SIZE)

context(InputContext)
fun countCharactersBeforeMarkerOfSize(markerSize: Int) = input.readText()
  .windowedSequence(markerSize)
  .takeWhile { it.toSet().size != markerSize }
  .count() + markerSize
