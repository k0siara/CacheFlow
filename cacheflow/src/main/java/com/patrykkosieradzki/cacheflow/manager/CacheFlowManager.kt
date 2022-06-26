package com.patrykkosieradzki.cacheflow.manager

import com.patrykkosieradzki.cacheflow.Cache
import kotlin.reflect.KClass

interface CacheFlowManager {
    fun <T : Cache> registerCache(cache: T): T

    suspend fun invalidate(cacheKClass: KClass<out Cache>? = null)
}
