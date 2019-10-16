package com.omeram.kotlin.githubkmp

import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlin.coroutines.CoroutineContext

expect fun platformName(): String

class Greeting {
    fun greeting(): String = "Hello, ${platformName()}"
}

internal expect val ApplicationDispatcher : CoroutineContext

expect class UserSettings

internal const val USER_SETTINGS_KEY: String = "USER_SETTINGS_KEY"
internal const val ORGANIZATION_KEY: String = "ORGANIZATION_KEY"
internal const val DEFAULT_ORGANIZATION: String = "raywenderlich"

