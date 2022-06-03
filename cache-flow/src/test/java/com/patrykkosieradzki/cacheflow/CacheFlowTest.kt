package com.patrykkosieradzki.cacheflow

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CacheFlowTest {

    // TODO: Write real tests later

    @Test
    fun `flow cache sample test`() = runBlocking {
        val somethingToCache by CacheFlow.lazyInMemoryCache<String?>(
            initialValue = null,
            onInvalidate = { null }
        )

        somethingToCache.save("super cache")

        println(somethingToCache.get())

        println(somethingToCache.flow.firstOrNull())
    }
}