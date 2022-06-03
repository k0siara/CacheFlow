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
