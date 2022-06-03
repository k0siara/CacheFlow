package com.patrykkosieradzki.cacheflow

import com.patrykkosieradzki.cacheflow.impl.InMemoryMapCacheFlow
import kotlinx.coroutines.flow.Flow

interface MapCacheFlow<K, V> : CacheFlow<HashMap<K, V>> {
    fun flow(key: K): Flow<V?>

    fun save(key: K, value: V)
    fun get(key: K): V?

    companion object {
        fun <K, V> inMemoryMapCache(
            initialValue: HashMap<K, V> = HashMap(),
            onInvalidate: (HashMap<K, V>) -> HashMap<K, V> = { it.apply { clear() } }
        ): MapCacheFlow<K, V> = InMemoryMapCacheFlow(
            initialValue = initialValue,
            onInvalidate = onInvalidate
        )

        fun <K, V> lazyInMemoryMapCache(
            initialValue: HashMap<K, V> = HashMap(),
            onInvalidate: (HashMap<K, V>) -> HashMap<K, V> = { it.apply { clear() } }
        ): Lazy<MapCacheFlow<K, V>> = lazy {
            InMemoryMapCacheFlow(
                initialValue = initialValue,
                onInvalidate = onInvalidate
            )
        }
    }
}
