package com.omeram.kotlin.githubkmp

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import platform.Foundation.NSUserDefaults

actual class UserSettings {
    private val delegate: NSUserDefaults = NSUserDefaults.standardUserDefaults
    val settings: Settings = AppleSettings(delegate)
}