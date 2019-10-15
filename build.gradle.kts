// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
//        mavenCentral()
//        maven("https://dl.bintray.com/jetbrains/kotlin-native-dependencies")
    }
    dependencies {
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.kotlin_serialization)
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
//    id ("org.jetbrains.kotlin.plugin.serialization") version Versions.org_jetbrains_kotlin
}

allprojects {
    repositories {
        google()
        jcenter()
//        maven("https://kotlin.bintray.com/kotlinx")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}