# CacheFlow

💾 Kotlin Library that allows you to easily manage Cache in your app.

[![Android CI](https://github.com/k0siara/CacheFlow/actions/workflows/android.yml/badge.svg)](https://github.com/k0siara/CacheFlow/actions/workflows/android.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.patrykkosieradzki/cacheflow.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.patrykkosieradzki%22%20AND%20a:%22cacheflow%22)
[![License](https://img.shields.io/github/license/k0siara/cacheflow.svg)](https://www.apache.org/licenses/LICENSE-2.0)

<!-- [![](https://jitpack.io/v/k0siara/cacheflow.svg)](https://jitpack.io/#k0siara/cacheflow) -->

![Kotlin Library that allows you to easily manage Cache in your app](/assets/cacheflow_banner.png)

## Problem
Caching is crucial for most apps, especialy on mobile, where resources, cpu, memory and battery life are limited. It allows you to store the data somewhere and use it later without the need to perform network requests repeatedly and waste resources I mentioned before. The most basic cache type is a in-memory cache that can be stored in an object, somewhere in the code. Other types of caches also allow you to store the data in files, local databases, etc.

The problem is that usually caching in apps is messy, the code is repeated many times and is often **non-thread-safe**, especially the in-memory cache. 

## Overview

CacheFlow library solves this problem for you, providing several ready-to-use caching mechanisms that are **thread-safe** and based on Kotlin Flow (for now only in-memory, more to come in the future)

One way to create a in-memory cache is to use MutableStateFlow. \
\
Here's and example Repository that holds cache after searching animals by a specific query:

``` kotlin
class AnimalCachedRepository {
    private val mapCache by lazy { MutableStateFlow(HashMap<String, List<Animal>>) }
    val flow by lazy { mapCache.asStateFlow() }
    
    fun cacheAnimalsByQuery(query: String, animals: List<Animal>) {
        mapCache.update { it.apply { put(query, animals) } }   
    }
    
    fun getCachedAnimalsByQuery(query: String): List<Animal>? {
        return mapCache.value.getOrElse(query, defaultValue = { null })
    }
    
    fun invalidate() {
        mapCache.update { it.apply { clear() } }
    }
}
```

Imagine writing this every time you want to cache something. I've been there and I've decided that it's time to do something about it.\
That's one of the reasons why CacheFlow was created 🙂.
\
\
Here's the same class, using CacheFlow:
``` kotlin
class AnimalCachedRepository : MapFlowCache<Query, List<Animal>> by MapCacheFlow.inMemoryMapCache() {
    
    // Added only to increase readability
    @JvmInline
    value class Query(val value: String)
}
```
\
Want to cache more things in one Repository? Here's how you can do that:
``` kotlin
class AnimalCachedRepository {
    private val mapCache by MapCacheFlow.lazyInMemoryMapCache<String, List<Animal>>()
    private val intCache by CacheFlow.lazyInMemoryCache<Int?>(
        initialValue = null,
        onInvalidate = { null }
    )
    // Other caches you need  
    
    // Caching logic
}
```

## How to include in your project

[![Maven Central](https://img.shields.io/maven-central/v/com.patrykkosieradzki/cacheflow.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.patrykkosieradzki%22%20AND%20a:%22cacheflow%22)

``` groovy
dependencies {
    implementation "com.patrykkosieradzki:cacheflow:1.0.0"
}

```

## SNAPSHOT 
[![CacheFlow](https://img.shields.io/static/v1?label=snapshot&message=CacheFlow&logo=apache%20maven&color=C71A36)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/patrykkosieradzki/cacheflow/) <br>

<details>
 <summary>See how to import the snapshot</summary>

### Including the SNAPSHOT
Snapshots of the current development version of CacheFlow are available, which track [the latest versions](https://s01.oss.sonatype.org/content/repositories/snapshots/com/patrykkosieradzki/cacheflow/).

To import snapshot versions on your project, add the code snippet below on your gradle file.
```Gradle
repositories {
   maven { url 'https://s01.oss.sonatype.org/content/repositories/snapshots/' }
}
```

Next, add the below dependency to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.patrykkosieradzki:cacheflow:1.0.0-SNAPSHOT"
}
```

</details>

## License

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
