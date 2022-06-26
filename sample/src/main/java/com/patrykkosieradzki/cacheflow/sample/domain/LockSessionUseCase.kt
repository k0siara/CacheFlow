package com.patrykkosieradzki.cacheflow.sample.domain

import com.patrykkosieradzki.cacheflow.manager.DefaultCacheFlowFlowManager
import com.patrykkosieradzki.cacheflow.sample.AppCaches

class LockSessionUseCase {
    suspend fun invoke() {
        DefaultCacheFlowFlowManager.invalidate(AppCaches.InvalidateOnSessionLockCache::class)
        // session lock logic...
    }
}
