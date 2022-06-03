package com.patrykkosieradzki.cacheflow

interface Cache {
    suspend fun invalidate()
}
