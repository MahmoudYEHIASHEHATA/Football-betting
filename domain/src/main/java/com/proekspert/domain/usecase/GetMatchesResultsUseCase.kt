package com.proekspert.domain.usecase

import com.proekspert.common.Resource
import com.proekspert.domain.entity.MatchResultEntity
import com.proekspert.domain.qualifiers.IoDispatcher
import com.proekspert.domain.repository.Repository
import com.proekspert.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMatchesResultsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<MatchResultEntity>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<MatchResultEntity>>> {
        return repository.getMatchesResults().flowOn(dispatcher)
    }
}