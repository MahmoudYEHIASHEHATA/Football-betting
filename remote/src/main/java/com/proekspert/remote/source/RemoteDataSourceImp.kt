package com.proekspert.remote.source

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchModel
import com.proekspert.data.model.MatchResultModel
import com.proekspert.data.repository.RemoteDataSource
import com.proekspert.qualifiers.IoDispatcher
import com.proekspert.remote.api.ApiService
import com.proekspert.remote.model.MatchDTO
import com.proekspert.remote.model.MatchResultDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val matchesMapper: Mapper<MatchDTO, MatchModel>,
    private val matchesResultsMapper: Mapper<MatchResultDTO, MatchResultModel>
) : RemoteDataSource {
    override suspend fun getAllMatches(): List<MatchModel> {
       return withContext(dispatcher){
           val networkData = apiService.getMatches()
            matchesMapper.fromList(networkData.matches)
        }
    }

    override suspend fun getAllMatchesResults(): List<MatchResultModel> {
       return withContext(dispatcher) {
           val networkData = apiService.getMatchesResults()
            matchesResultsMapper.fromList(networkData.matches)
       }
    }
}