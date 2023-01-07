package com.proekspert.domain.usecase

import com.proekspert.domain.repository.Repository
import javax.inject.Inject

class DeleteMatchesResultsUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun deleteAllMatchesResults(): Int {
        return repository.deleteAllMatchesResults()
    }
}