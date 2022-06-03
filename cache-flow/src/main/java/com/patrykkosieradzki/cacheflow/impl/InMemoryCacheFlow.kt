package com.patrykkosieradzki.cacheflow.impl

import com.patrykkosieradzki.cacheflow.CacheFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class InMemoryCacheFlow<T>(
    private val initialValue: T,
    private val onInvalidate: (T) -> T
) : CacheFlow<T> {
    private val cacheState by lazy { MutableStateFlow(initialValue) }
    override val flow by lazy { cacheState.asStateFlow() }

    override fun get(): T = cacheState.value

    override fun save(value: T) {
        cacheState.update { value }
    }

    override fun save(updateFunc: (T) -> T) {
        cacheState.update(updateFunc)
    }

    override suspend fun invalidate() {
        cacheState.update(onInvalidate)
    }
}
