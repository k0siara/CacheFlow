package com.patrykkosieradzki.cacheflow.impl

import com.patrykkosieradzki.cacheflow.CacheFlow
import com.patrykkosieradzki.cacheflow.ListCacheFlow

internal class InMemoryListCacheFlow<T>(
    private val initialValue: List<T>,
    private val onInvalidate: (List<T>) -> List<T>
) : ListCacheFlow<T>, CacheFlow<List<T>> by CacheFlow.inMemoryCache(
    initialValue = initialValue,
    onInvalidate = onInvalidate
)
