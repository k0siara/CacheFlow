package com.patrykkosieradzki.flowcache

interface Cache {
    suspend fun invalidate()
}
