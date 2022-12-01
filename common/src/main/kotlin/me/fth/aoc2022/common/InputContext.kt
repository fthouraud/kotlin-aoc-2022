package me.fth.aoc2022.common

import java.net.URL

interface InputContext {
  val input: URL
}

fun <R : Any?> withInput(fileInput: URL, block: InputContext.() -> R): R = with(
  object : InputContext {
    override val input: URL = fileInput
  },
  block
)
