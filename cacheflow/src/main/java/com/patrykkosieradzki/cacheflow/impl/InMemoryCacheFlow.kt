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
package com.patrykkosieradzki.cacheflow.impl

import com.patrykkosieradzki.cacheflow.CacheFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class InMemoryCacheFlow<T>(
    private val initialValue: T,
    private val onInvalidate: (T) -> T
) : CacheFlow<T> {
    private val cacheState by lazy { MutableStateFlow(initialValue) }
    override val flow by lazy { cacheState.asStateFlow() }

    override fun get(): T = cacheState.value

    override fun save(value: T) {
        cacheState.update { value }
    }

    override fun save(updateFunc: (T) -> T) {
        cacheState.update(updateFunc)
    }

    override suspend fun invalidate() {
        cacheState.update(onInvalidate)
    }
}
