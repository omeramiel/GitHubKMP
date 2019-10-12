package com.omeram.kotlin.githubkmp

import platform.UIKit.UIDevice

actual fun platformName() : String = UIDevice.currentDevice.systemName()