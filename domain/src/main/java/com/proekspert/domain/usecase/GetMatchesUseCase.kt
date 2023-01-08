package com.proekspert.domain.usecase

import com.proekspert.domain.model.Match
import com.proekspert.domain.repository.Repository
import com.proekspert.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<List<Match>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<List<Match>> {
        return repository.getFreshMatches()
    }
}