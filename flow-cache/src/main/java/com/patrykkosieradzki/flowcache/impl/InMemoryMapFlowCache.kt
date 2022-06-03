package com.patrykkosieradzki.flowcache.impl

import com.patrykkosieradzki.flowcache.FlowCache
import com.patrykkosieradzki.flowcache.MapFlowCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class InMemoryMapFlowCache<K, V>(
    private val initialValue: HashMap<K, V> = HashMap(),
    private val onInvalidate: (HashMap<K, V>) -> HashMap<K, V> = { it.apply { clear() } }
) : MapFlowCache<K, V>,
    FlowCache<HashMap<K, V>> by InMemoryFlowCache(
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
