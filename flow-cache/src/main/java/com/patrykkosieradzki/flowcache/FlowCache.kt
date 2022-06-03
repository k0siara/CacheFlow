package com.patrykkosieradzki.flowcache

import com.patrykkosieradzki.flowcache.impl.InMemoryFlowCache
import kotlinx.coroutines.flow.Flow

interface FlowCache<T> : Cache {
    val flow: Flow<T>
    fun get(): T

    fun save(value: T)
    fun save(updateFunc: (T) -> T)

    companion object {
        fun <T> inMemoryCache(
            initialValue: T,
            onInvalidate: (T) -> T
        ): FlowCache<T> = InMemoryFlowCache(
            initialValue = initialValue,
            onInvalidate = onInvalidate
        )

        fun <T> lazyInMemoryCache(
            initialValue: T,
            onInvalidate: (T) -> T
        ): Lazy<FlowCache<T>> = lazy {
            InMemoryFlowCache(
                initialValue = initialValue,
                onInvalidate = onInvalidate
            )
        }
    }
}
