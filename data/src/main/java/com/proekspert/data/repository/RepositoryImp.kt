package com.proekspert.data.repository

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchModel
import com.proekspert.data.model.MatchResultModel
import com.proekspert.domain.model.Match
import com.proekspert.domain.model.MatchResult
import com.proekspert.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val matchMapper: Mapper<MatchModel, Match>,
    private val matchResultMapper: Mapper<MatchResultModel, MatchResult>
) : Repository {
    override suspend fun getFreshMatches(): Flow<List<Match>> {
        return localDataSource.getAllMatches()
            .onStart {
                refreshMatchesCache()
            }.map { matchMapper.fromList(it) }
    }

    override suspend fun getCachedMatches(): Flow<List<Match>> {
        return localDataSource.getAllMatches().map { matchMapper.fromList(it) }
    }

    override suspend fun getCachedMatchesResults(): Flow<List<MatchResult>> {
        return localDataSource.getAllMatchesResults().map { matchResultMapper.fromList(it) }
    }

    override suspend fun editMatch(match: Match) {
        localDataSource.editMatch(matchMapper.to(match))
    }

    override suspend fun deleteAllMatches(): Int {
        return localDataSource.deleteAllMatches()
    }

    override suspend fun deleteAllMatchesResults(): Int {
        return localDataSource.deleteAllMatchesResults()
    }

    private suspend fun refreshMatchesCache() {
        // Get data from RemoteDataSource
        val data = remoteDataSource.getAllMatches()
        //Delete the old matches if exist
        localDataSource.deleteAllMatches()
        // Save to local or update if exist
        localDataSource.addAllMatches(data)
    }

    override suspend fun getFreshMatchesResults(): Flow<List<MatchResult>> {
        return localDataSource.getAllMatchesResults()
            .onStart {
                refreshMatchesResultsCache()
            }.map { matchResultMapper.fromList(it) }
    }

    override suspend fun existsPredictions(): Boolean {
        return localDataSource.existsPredictions()
    }

    override suspend fun isMatchesTableEmpty(): Boolean {
        return localDataSource.isMatchesTableEmpty()
    }

    override suspend fun isMatchesResultsTableEmpty(): Boolean {
        return localDataSource.isMatchesResultsTableEmpty()
    }


    private suspend fun refreshMatchesResultsCache() {
        // Get data from RemoteDataSource
        val data = remoteDataSource.getAllMatchesResults()
        //Delete the old matches results if exist
        localDataSource.deleteAllMatchesResults()
        // Save to local or update if exist
        localDataSource.addAllMatchesResults(data)
    }
}