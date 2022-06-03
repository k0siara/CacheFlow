# CacheFlow

📦 Kotlin Library that allows you to easily manage Cache in your app.

[![Android CI](https://github.com/k0siara/CacheFlow/actions/workflows/android.yml/badge.svg)](https://github.com/k0siara/CacheFlow/actions/workflows/android.yml) [![](https://jitpack.io/v/k0siara/cacheflow.svg)](https://jitpack.io/#k0siara/cacheflow)

## How to include in your project

Add `jitpack` repository to your project root `build.gradle` (or `build.gradle.kts`)

``` groovy
// build.gradle
allprojects {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
    // ...
}
```

For newer projects (Gradle above 6.8), you need to also update the `settings.gradle` file's `dependencyResolutionManagement` block

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }  // <--
        jcenter() // Warning: this repository is going to shut down soon
    }
}
```

Add the dependency to your module's `build.gradle` (or `build.gradle.kts`)

``` groovy
dependencies {
  implementation 'com.github.k0siara:cacheflow:<version>'
}

```
See the top of this README to find the newest version ⬆️

License
=======

    Copyright 2022 Patryk Kosieradzki.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
