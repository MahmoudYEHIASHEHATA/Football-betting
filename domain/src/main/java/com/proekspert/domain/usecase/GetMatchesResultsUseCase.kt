package com.proekspert.domain.usecase

import com.proekspert.domain.model.MatchResult
import com.proekspert.domain.repository.Repository
import com.proekspert.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchesResultsUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<List<MatchResult>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<List<MatchResult>> {
        return repository.getMatchesResults()
    }
}