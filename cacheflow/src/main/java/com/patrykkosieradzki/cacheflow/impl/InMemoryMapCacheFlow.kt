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
import com.patrykkosieradzki.cacheflow.MapCacheFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class InMemoryMapCacheFlow<K, V>(
    private val initialValue: HashMap<K, V> = HashMap(),
    private val onInvalidate: (HashMap<K, V>) -> HashMap<K, V> = { it.apply { clear() } }
) : MapCacheFlow<K, V>,
    CacheFlow<HashMap<K, V>> by InMemoryCacheFlow(
        initialValue = initialValue,
        onInvalidate = onInvalidate
    ) {

    override fun flow(key: K): Flow<V?> = flow.map { it[key] }

    override fun save(key: K, value: V) {
        save { it.apply { put(key, value) } }
    }

    override fun get(key: K): V? {
        return get().getOrElse(key, defaultValue = { null })
    }
}
