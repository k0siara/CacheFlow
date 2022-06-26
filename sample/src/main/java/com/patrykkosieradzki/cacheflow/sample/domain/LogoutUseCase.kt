package com.patrykkosieradzki.cacheflow.sample.domain

import com.patrykkosieradzki.cacheflow.manager.DefaultCacheFlowFlowManager
import com.patrykkosieradzki.cacheflow.sample.AppCaches

class LogoutUseCase {
    suspend fun invoke() {
        DefaultCacheFlowFlowManager.invalidate(AppCaches.InvalidateOnLogoutCache::class)
        // logout logic...
    }
}
