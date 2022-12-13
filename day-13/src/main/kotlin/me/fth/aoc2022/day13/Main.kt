package me.fth.aoc2022.day13

import me.fth.aoc2022.common.InputContext
import me.fth.aoc2022.common.printMeasuredTime
import me.fth.aoc2022.common.withInput
import java.net.URL

val input: URL = object {}.javaClass.getResource("/distress-signal.txt")

fun main() = withInput(input) {
  printMeasuredTime {
    println("Sum of left packages' indices that are in order: ${sumIndicesOfLeftPackagesInOrder()}")
    println("Decoder key: ${findDecoderKey()}")
  }
}

context(InputContext)
fun sumIndicesOfLeftPackagesInOrder() = lineSequence().windowed(size = 2, step = 3) { (a, b) -> parse(a) to parse(b) }
  .foldIndexed(0) { index, value, (a, b) ->
    if (compareSignals(a, b) <= 0) value + index + 1
    else value
  }

fun parse(raw: String): Any {
  return parseFrom(raw).first
}

fun parseFrom(raw: String, startingIndex: Int = 1): Pair<Any, Int> {
  val currentList: MutableList<Any> = mutableListOf()
  var currentNumber = ""
  var currentIndex = startingIndex
  while (currentIndex <= raw.lastIndex) {
    when (raw[currentIndex]) {
      in '0'..'9' -> currentNumber += raw[currentIndex]
      ',' -> currentNumber.takeIf { it.isNotBlank() }?.let {
        currentList += it.toInt()
        currentNumber = ""
      }

      '[' -> parseFrom(raw, currentIndex + 1).let { (signal, closingIndex) ->
        currentList += signal
        currentIndex = closingIndex
      }

      ']' -> {
        currentNumber.takeIf { it.isNotBlank() }?.let {
          currentList += it.toInt()
          currentNumber = ""
        }
        return currentList to currentIndex
      }
    }
    currentIndex += 1
  }
  error("Failed to parse the signal.")
}

fun compareSignals(a: Any, b: Any): Int {
  if (a is Int && b is Int) {
    return a compareTo b
  } else if (a is List<*> && b is List<*>) {
    val iterator = a.iterator()
    val otherIterator = b.iterator()
    while (iterator.hasNext()) {
      if (otherIterator.hasNext()) {
        val comparison = compareSignals(iterator.next()!!, otherIterator.next()!!)
        if (comparison != 0) return comparison
        else continue
      }
      return 1
    }
    if (otherIterator.hasNext()) return -1
  } else {
    val left = a.takeIf { it is Int }?.let { listOf(it) } ?: a
    val right = b.takeIf { it is Int }?.let { listOf(it) } ?: b
    return compareSignals(left, right)
  }
  return 0
}

val firstDivider = listOf(listOf(2))
val secondDivider = listOf(listOf(6))

context(InputContext)
fun findDecoderKey() = lineSequence().windowed(size = 2, step = 3) { (a, b) -> listOf(parse(a), parse(b)) }
  .flatten()
  .toMutableList()
  .also {
    it.add(firstDivider)
    it.add(secondDivider)
  }
  .sortedWith(AnyComparator)
  .let { (it.indexOf(firstDivider) + 1) * (it.indexOf(secondDivider) + 1) }

object AnyComparator : Comparator<Any> {
  override fun compare(o1: Any, o2: Any): Int {
    return compareSignals(o1, o2)
  }
}
