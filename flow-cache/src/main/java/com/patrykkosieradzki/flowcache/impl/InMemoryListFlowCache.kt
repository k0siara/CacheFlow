package com.patrykkosieradzki.flowcache.impl

import com.patrykkosieradzki.flowcache.FlowCache
import com.patrykkosieradzki.flowcache.ListFlowCache

internal class InMemoryListFlowCache<T>(
    private val initialValue: List<T>,
    private val onInvalidate: (List<T>) -> List<T>
) : ListFlowCache<T>, FlowCache<List<T>> by FlowCache.inMemoryCache(
    initialValue = initialValue,
    onInvalidate = onInvalidate
)
