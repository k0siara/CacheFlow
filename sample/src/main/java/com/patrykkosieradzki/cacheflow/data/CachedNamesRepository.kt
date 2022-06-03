package com.patrykkosieradzki.cacheflow.data

import com.patrykkosieradzki.cacheflow.ListCacheFlow

class CachedNamesRepository : ListCacheFlow<String> by ListCacheFlow.inMemoryListCache()