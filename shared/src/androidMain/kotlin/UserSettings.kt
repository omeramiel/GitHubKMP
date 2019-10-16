package com.omeram.kotlin.githubkmp

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

actual class UserSettings(context: Context) {
    private val delegate: SharedPreferences =
        context.getSharedPreferences(USER_SETTINGS_KEY, Context.MODE_PRIVATE)
    val settings: Settings = AndroidSettings(delegate)
}