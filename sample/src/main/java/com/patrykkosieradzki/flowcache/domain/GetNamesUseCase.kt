package com.patrykkosieradzki.flowcache.domain

import com.patrykkosieradzki.flowcache.data.CachedNamesRepository
import com.patrykkosieradzki.flowcache.data.NamesRepository

// Example of simple use case that returns cached data if it's available
// If data is not cached it will fetch data from network and cache it

class GetNamesUseCase(
    private val namesRepository: NamesRepository = NamesRepository(),
    private val cachedNamesRepository: CachedNamesRepository = CachedNamesRepository()
) {
    suspend fun invoke(): List<String> {
        return cachedNamesRepository.get().ifEmpty {
            namesRepository.getNames().also { names ->
                cachedNamesRepository.save(names)
            }
        }
    }
}
