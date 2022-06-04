# CacheFlow

📦 Kotlin Library that allows you to easily manage Cache in your app.

[![Android CI](https://github.com/k0siara/CacheFlow/actions/workflows/android.yml/badge.svg)](https://github.com/k0siara/CacheFlow/actions/workflows/android.yml) 
[![License](https://img.shields.io/github/license/k0siara/cacheflow.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://jitpack.io/v/k0siara/cacheflow.svg)](https://jitpack.io/#k0siara/cacheflow)

## Problem
Caching is crucial for most apps, especialy on mobile, where resources, cpu, memory and battery life are limited. It allows you to store the data somewhere and use it later without the need to perform network requests repeatedly and waste resources I mentioned before. The most basic cache type is a in-memory cache that can be stored in an object, somewhere in the code. Other types of caches also allow you to store the data in files, local databases, etc.

The problem is that usually caching in apps is messy, the code is repeated many times and is often **non-thread-safe**, especially the in-memory cache. 

## Overview
CacheFlow library solves this problem for you, prioviding several ready-to-use caching mechanisms (for now only in-memory, more to come in the future)

One way to create a in-memory cache is to use MutableStateFlow. See the example below:

``` kotlin
class AnimalRepository {
    private val cacheMap by lazy { MutableStateFlow(HashMap<String, List<Animal>>) }
    val flow by lazy { cacheMap.asStateFlow() }
    
    fun cacheAnimalsByQuery(query: String, animals: List<Animal>) {
        cacheMap.update { it.apply { put(query, animals) } }   
    }
    
    fun getCachedAnimalsByQuery(query: String): List<Animal>? {
        return cacheMap.value.getOrElse(query, defaultValue = { null })
    }
    
    fun invalidate() {
        cacheMap.update { it.apply { clear() } }
    }
}
```

``` kotlin
class AnimalRepository {
    private val cacheMap by MapCacheFlow.lazyInMemoryMapCache<String, List<Animal>>()
    val flow by lazy { cacheMap.flow }
    
    fun cacheAnimalsByQuery(query: String, animals: List<Animal>) {
        cacheMap.save(query, animals)
    }
    
    fun getCachedAnimalsByQuery(query: String): List<Animal>? {
        return cacheMap.get(query)
    }
    
    fun invalidate() {
        cacheMap.invalidate()
    }
}
```

``` kotlin
class AnimalRepository : MapFlowCache<Query, List<Animal>> by MapCacheFlow.inMemoryMapCache() {
    
    // Added only to increase readability
    @JvmInline
    value class Query(val value: String)
}
```

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
        maven { url 'https://jitpack.io' }  // <-- Add this
    }
}
```

Add the dependency to your module's `build.gradle` (or `build.gradle.kts`)

``` groovy
dependencies {
  implementation "com.github.k0siara:cacheflow:<version>"
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
