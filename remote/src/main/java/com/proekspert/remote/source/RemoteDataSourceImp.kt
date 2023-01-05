package com.proekspert.remote.source

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.data.repository.RemoteDataSource
import com.proekspert.remote.api.ApiService
import com.proekspert.remote.model.Match
import com.proekspert.remote.model.MatchResult
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val matchesMapper: Mapper<Match, MatchDTO>,
    private val matchesResultsMapper: Mapper<MatchResult, MatchResultDTO>
) : RemoteDataSource {
    override suspend fun getAllMatches(): List<MatchDTO> {
        val networkData = apiService.getMatches()
        return matchesMapper.fromList(networkData.matches)
    }

    override suspend fun getAllMatchesResults(): List<MatchResultDTO> {
        val networkData = apiService.getMatchesResults()
        return matchesResultsMapper.fromList(networkData.matches)
    }
}