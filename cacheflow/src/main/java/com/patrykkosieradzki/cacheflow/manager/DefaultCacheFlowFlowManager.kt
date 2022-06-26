package com.patrykkosieradzki.cacheflow.manager

import com.patrykkosieradzki.cacheflow.Cache
import kotlin.reflect.KClass

object DefaultCacheFlowFlowManager : CacheFlowManager {
    private val caches: MutableList<Cache> = mutableListOf()

    override fun <T : Cache> registerCache(cache: T): T {
        caches.add(cache)
        return cache
    }

    override suspend fun invalidate(cacheKClass: KClass<out Cache>?) {
        val clazz = cacheKClass?.java
        val cachesToInvalidate = when {
            clazz != null -> caches.filterIsInstance(clazz)
            else -> caches
        }
        cachesToInvalidate.forEach { cache -> cache.invalidate() }
    }
}
