package com.omeram.kotlin.githubkmp

import android.os.Build

actual fun platformName(): String = "Android ${Build.VERSION.RELEASE}"