/*
 * Copyright (C) 2022 Patryk Kosieradzki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.patrykkosieradzki.cacheflow

import com.patrykkosieradzki.cacheflow.impl.InMemoryCacheFlow
import kotlinx.coroutines.flow.Flow

interface CacheFlow<T> : Cache {
    val flow: Flow<T>
    fun get(): T

    fun save(value: T)
    fun save(updateFunc: (T) -> T)

    companion object {
        fun <T> inMemoryCache(
            initialValue: T,
            onInvalidate: (T) -> T
        ): CacheFlow<T> = InMemoryCacheFlow(
            initialValue = initialValue,
            onInvalidate = onInvalidate
        )

        fun <T> lazyInMemoryCache(
            initialValue: T,
            onInvalidate: (T) -> T
        ): Lazy<CacheFlow<T>> = lazy {
            InMemoryCacheFlow(
                initialValue = initialValue,
                onInvalidate = onInvalidate
            )
        }
    }
}
