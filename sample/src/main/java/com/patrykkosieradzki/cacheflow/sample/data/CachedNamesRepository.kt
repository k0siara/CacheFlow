package com.patrykkosieradzki.cacheflow.sample.data

import com.patrykkosieradzki.cacheflow.ListCacheFlow

class CachedNamesRepository : ListCacheFlow<String> by ListCacheFlow.inMemoryListCache()