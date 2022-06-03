package com.patrykkosieradzki.flowcache

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FlowCacheTest {

    // TODO: Write real tests later

    @Test
    fun `flow cache sample test`() = runBlocking {
        val somethingToCache by FlowCache.lazyInMemoryCache<String?>(
            initialValue = null,
            onInvalidate = { null }
        )

        somethingToCache.save("super cache")

        println(somethingToCache.get())

        println(somethingToCache.flow.firstOrNull())
    }
}