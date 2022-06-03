package com.patrykkosieradzki.flowcache.data

import com.patrykkosieradzki.flowcache.ListFlowCache

class CachedNamesRepository : ListFlowCache<String> by ListFlowCache.inMemoryListCache()