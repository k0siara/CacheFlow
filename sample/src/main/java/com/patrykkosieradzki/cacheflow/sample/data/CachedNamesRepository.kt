package com.patrykkosieradzki.cacheflow.sample.data

import com.patrykkosieradzki.cacheflow.ListCacheFlow
import com.patrykkosieradzki.cacheflow.manager.DefaultCacheFlowFlowManager
import com.patrykkosieradzki.cacheflow.sample.AppCaches

class CachedNamesRepository : ListCacheFlow<String> by ListCacheFlow.inMemoryListCache(),
    AppCaches.InvalidateOnLogoutCache,
    AppCaches.InvalidateOnSessionLockCache {

    init {
        // just for demonstration, should be done in DI
        DefaultCacheFlowFlowManager.registerCache(CachedNamesRepository())
    }
}