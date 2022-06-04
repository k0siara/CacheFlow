/*
 * Copyright (C) 2022 Patryk Kosieradzki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
