package com.patrykkosieradzki.cacheflow

object Versions {
    internal const val ANDROID_GRADLE_PLUGIN = "7.2.0"
    internal const val ANDROID_GRADLE_SPOTLESS = "6.3.0"
    internal const val GRADLE_NEXUS_PUBLISH_PLUGIN = "1.1.0"

    internal const val KOTLIN = "1.6.21"
    internal const val COROUTINES_CORE = "1.6.2"

    internal const val JUNIT = "4.13.2"
}

object Dependencies {
    const val androidGradlePlugin =
        "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val spotlessGradlePlugin =
        "com.diffplug.spotless:spotless-plugin-gradle:${Versions.ANDROID_GRADLE_SPOTLESS}"
    const val gradleNexusPublishPlugin =
        "io.github.gradle-nexus:publish-plugin:${Versions.GRADLE_NEXUS_PUBLISH_PLUGIN}"

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"
    }

    const val junit = "junit:junit:${Versions.JUNIT}"
}