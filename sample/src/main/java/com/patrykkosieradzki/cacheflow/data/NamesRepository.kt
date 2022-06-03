package com.patrykkosieradzki.cacheflow.data

import kotlinx.coroutines.delay

class NamesRepository {

    // Simulates a long network call
    suspend fun getNames(): List<String> {
        delay(2000)
        return listOf("John Doe", "Kate Moss", "Philip Adams")
    }
}