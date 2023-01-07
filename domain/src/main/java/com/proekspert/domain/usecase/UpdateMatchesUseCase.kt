package com.proekspert.domain.usecase

import com.proekspert.domain.model.Match
import com.proekspert.domain.repository.Repository
import javax.inject.Inject

class UpdateMatchesUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun updateMatch(match: Match) {
         repository.editMatch(match)
    }
}