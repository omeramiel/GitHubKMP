package com.omeram.kotlin.githubkmp

import kotlin.coroutines.CoroutineContext

internal expect val ApplicationDispatcher : CoroutineContext

expect fun platformName(): String

class Greeting {
    fun greeting(): String = "Hello, ${platformName()}"
}