package com.proekspert.local.sourc

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchModel
import com.proekspert.data.model.MatchResultModel
import com.proekspert.data.repository.LocalDataSource
import com.proekspert.local.database.MatchesDAO
import com.proekspert.local.database.MatchesResultsDAO
import com.proekspert.local.model.MatchEntity
import com.proekspert.local.model.MatchResultEntity
import com.proekspert.qualifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val matchesDAO: MatchesDAO,
    private val matchesResultsDAO: MatchesResultsDAO,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val matchMapper: Mapper<MatchEntity, MatchModel>,
    private val matchResultMapper: Mapper<MatchResultEntity, MatchResultModel>
) : LocalDataSource {

    override suspend fun addAllMatches(matchesList: List<MatchModel>): List<Long> {
        return withContext(dispatcher) {
            val matchLocalModel = matchMapper.toList(matchesList)
            matchesDAO.addAllMatches(matchLocalModel)
        }
    }

    override suspend fun editMatch(match: MatchModel) {
        withContext(dispatcher) {
            matchesDAO.updateMatch(matchMapper.to(match))
        }
    }

    override suspend fun addAllMatchesResults(matchesResults: List<MatchResultModel>): List<Long> {
        return withContext(dispatcher) {
            val matchResultLocalModel = matchResultMapper.toList(matchesResults)
            matchesResultsDAO.addAllMatchesResults(matchResultLocalModel)
        }
    }

    override suspend fun getAllMatches(): Flow<List<MatchModel>> {
        return withContext(dispatcher) {
            val matchLocalModel = matchesDAO.getAllMatches()
            matchLocalModel.map { matchMapper.fromList(it) }
        }
    }

    override suspend fun getAllMatchesResults(): Flow<List<MatchResultModel>> {
        return withContext(dispatcher) {
            val matchLocalModel = matchesResultsDAO.getAllMatchesResults()
            matchLocalModel.map { matchResultMapper.fromList(it) }
        }
    }

    override suspend fun deleteAllMatches(): Int {
        return withContext(dispatcher) {
            matchesDAO.deleteAllMatches()
        }
    }

    override suspend fun deleteAllMatchesResults(): Int {
        return withContext(dispatcher) {
            matchesResultsDAO.deleteAllMatchesResults()
        }
    }

    override suspend fun existsPredictions(): Boolean {
        return withContext(dispatcher) {
            matchesDAO.existsPredictions()
        }
    }

    override suspend fun isMatchesTableEmpty(): Boolean {
        return withContext(dispatcher) {
            matchesDAO.isEmpty()
        }
    }

    override suspend fun isMatchesResultsTableEmpty(): Boolean {
        return withContext(dispatcher) {
            matchesResultsDAO.isEmpty()
        }
    }
}