import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import kotlin.script.experimental.jvm.util.KotlinJars.stdlib

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id ("kotlinx-serialization")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(15)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

kotlin {
    android()

    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets["commonMain"].dependencies {
        api(kotlin("stdlib-common"))
        implementation(Libs.ktor_client_core)
        implementation(Libs.kotlinx_coroutines_core_common)
        implementation(Libs.kotlinx_serialization_runtime)
        implementation(Libs.multiplatform_settings)
    }

    sourceSets["androidMain"].dependencies {
        api(kotlin("stdlib-jdk8"))
        implementation(Libs.ktor_client_android)
        implementation(Libs.kotlinx_coroutines_android)
    }

    sourceSets["iosMain"].dependencies {
        implementation(Libs.ktor_client_ios)
        implementation(Libs.kotlinx_coroutines_core_native)
        implementation(Libs.kotlinx_serialization_runtime_native)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"

    //selecting the right configuration for the iOS framework depending on the Xcode environment variables
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)

    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)