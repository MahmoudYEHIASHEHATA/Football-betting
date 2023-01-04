package com.proekspert.domain.usecase

import com.proekspert.common.Resource
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.domain.qualifiers.IoDispatcher
import com.proekspert.domain.repository.Repository
import com.proekspert.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<MatchEntity>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<MatchEntity>>> {
        return repository.getMatches().flowOn(dispatcher)
    }
}