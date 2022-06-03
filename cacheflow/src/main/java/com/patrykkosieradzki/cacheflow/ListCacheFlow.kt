package com.patrykkosieradzki.cacheflow

import com.patrykkosieradzki.cacheflow.impl.InMemoryListCacheFlow

interface ListCacheFlow<T> : CacheFlow<List<T>> {

    companion object {
        fun <T> inMemoryListCache(
            initialValue: List<T> = emptyList(),
            onInvalidate: (List<T>) -> List<T> = { emptyList() }
        ): ListCacheFlow<T> = InMemoryListCacheFlow(
            initialValue = initialValue,
            onInvalidate = onInvalidate
        )

        fun <T> lazyInMemoryListCache(
            initialValue: List<T> = emptyList(),
            onInvalidate: (List<T>) -> List<T> = { emptyList() }
        ): Lazy<ListCacheFlow<T>> = lazy {
            InMemoryListCacheFlow(
                initialValue = initialValue,
                onInvalidate = onInvalidate
            )
        }
    }
}
