package com.proekspert.domain.usecase

import com.proekspert.domain.repository.Repository
import javax.inject.Inject

class DeleteMatchesUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun deleteAllMatches(): Int {
        return repository.deleteAllMatches()
    }
}