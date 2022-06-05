package com.patrykkosieradzki.cacheflow

object Configuration {
    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 0

    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val versionCode = majorVersion * 1000 + minorVersion * 100 + patchVersion
    const val snapshotVersionName = "$versionName-SNAPSHOT"

    const val artifactGroup = "com.patrykkosieradzki"
}