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
