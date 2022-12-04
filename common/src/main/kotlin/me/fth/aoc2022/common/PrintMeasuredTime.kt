package me.fth.aoc2022.common

import kotlin.time.measureTime

fun printMeasuredTime(block: () -> Unit) = println("Executed in: ${measureTime(block)}")
