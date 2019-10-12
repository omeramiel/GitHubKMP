package com.omeram.kotlin.githubkmp

expect fun platformName(): String

class Greeting {
    fun greeting(): String = "Hello, ${platformName()}"
}