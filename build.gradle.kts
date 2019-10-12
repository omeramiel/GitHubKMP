// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.com_android_tools_build_gradle)
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}