package com.patrykkosieradzki.cacheflow.sample

import com.patrykkosieradzki.cacheflow.Cache

object AppCaches {
    interface InvalidateOnLogoutCache : Cache

    interface InvalidateOnSessionLockCache : Cache
}
