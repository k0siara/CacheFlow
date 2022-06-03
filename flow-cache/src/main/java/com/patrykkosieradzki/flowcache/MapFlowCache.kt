package com.patrykkosieradzki.flowcache

import com.patrykkosieradzki.flowcache.impl.InMemoryMapFlowCache
import kotlinx.coroutines.flow.Flow

interface MapFlowCache<K, V> : FlowCache<HashMap<K, V>> {
    fun flow(key: K): Flow<V?>

    fun save(key: K, value: V)
    fun get(key: K): V?

    companion object {
        fun <K, V> inMemoryMapCache(
            initialValue: HashMap<K, V> = HashMap(),
            onInvalidate: (HashMap<K, V>) -> HashMap<K, V> = { it.apply { clear() } }
        ): MapFlowCache<K, V> = InMemoryMapFlowCache(
            initialValue = initialValue,
            onInvalidate = onInvalidate
        )

        fun <K, V> lazyInMemoryMapCache(
            initialValue: HashMap<K, V> = HashMap(),
            onInvalidate: (HashMap<K, V>) -> HashMap<K, V> = { it.apply { clear() } }
        ): Lazy<MapFlowCache<K, V>> = lazy {
            InMemoryMapFlowCache(
                initialValue = initialValue,
                onInvalidate = onInvalidate
            )
        }
    }
}
