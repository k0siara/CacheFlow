package com.patrykkosieradzki.flowcache

import com.patrykkosieradzki.flowcache.impl.InMemoryListFlowCache

interface ListFlowCache<T> : FlowCache<List<T>> {

    companion object {
        fun <T> inMemoryListCache(
            initialValue: List<T> = emptyList(),
            onInvalidate: (List<T>) -> List<T> = { emptyList() }
        ): ListFlowCache<T> = InMemoryListFlowCache(
            initialValue = initialValue,
            onInvalidate = onInvalidate
        )

        fun <T> lazyInMemoryListCache(
            initialValue: List<T> = emptyList(),
            onInvalidate: (List<T>) -> List<T> = { emptyList() }
        ): Lazy<ListFlowCache<T>> = lazy {
            InMemoryListFlowCache(
                initialValue = initialValue,
                onInvalidate = onInvalidate
            )
        }
    }
}
