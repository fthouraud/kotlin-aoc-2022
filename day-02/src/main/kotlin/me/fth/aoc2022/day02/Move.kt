package me.fth.aoc2022.day02

const val WIN_POINT = 6
const val DRAW_POINT = 3

const val ROCK = 'A'
const val PAPER = 'B'
const val SCISSORS = 'C'
const val LOOSE_STRATEGY = 'X'
const val DRAW_STRATEGY = 'Y'
const val WIN_STRATEGY = 'Z'

sealed class Move(val point: Int) {
  object Rock : Move(1) {
    override fun versus(move: Move): Pair<Int, Int> = when (move) {
      Rock -> point + DRAW_POINT to move.point + DRAW_POINT
      Paper -> point to move.point + WIN_POINT
      Scissors -> point + WIN_POINT to move.point
    }

    override fun expectedMove(strategy: Char) = when (strategy) {
      LOOSE_STRATEGY -> Scissors
      WIN_STRATEGY -> Paper
      else -> this
    }
  }

  object Paper : Move(2) {
    override fun versus(move: Move): Pair<Int, Int> = when (move) {
      Rock -> point + WIN_POINT to move.point
      Paper -> point + DRAW_POINT to move.point + DRAW_POINT
      Scissors -> point to move.point + WIN_POINT
    }

    override fun expectedMove(strategy: Char) = when (strategy) {
      LOOSE_STRATEGY -> Rock
      WIN_STRATEGY -> Scissors
      else -> this
    }
  }

  object Scissors : Move(3) {
    override fun versus(move: Move): Pair<Int, Int> = when (move) {
      Rock -> point to move.point + WIN_POINT
      Paper -> point + WIN_POINT to move.point
      Scissors -> point + DRAW_POINT to move.point + DRAW_POINT
    }

    override fun expectedMove(strategy: Char) = when (strategy) {
      LOOSE_STRATEGY -> Paper
      WIN_STRATEGY -> Rock
      else -> this
    }
  }

  abstract infix fun versus(move: Move): Pair<Int, Int>

  abstract fun expectedMove(strategy: Char): Move

  companion object {
    fun parse(value: Char): Move = when (value) {
      ROCK, LOOSE_STRATEGY -> Rock
      PAPER, DRAW_STRATEGY -> Paper
      SCISSORS, WIN_STRATEGY -> Scissors
      else -> throw IllegalArgumentException("$value is not a valid move.")
    }
  }
}

fun Char.toMove() = Move.parse(this)
