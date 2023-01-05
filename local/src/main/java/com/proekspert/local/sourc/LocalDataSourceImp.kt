package com.proekspert.local.sourc

import com.proekspert.common.Mapper
import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO
import com.proekspert.data.repository.LocalDataSource
import com.proekspert.local.database.MatchesDAO
import com.proekspert.local.database.MatchesResultsDAO
import com.proekspert.local.model.MatchLocalModel
import com.proekspert.local.model.MatchResultLocalModel
import javax.inject.Inject

/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val matchesDAO: MatchesDAO,
    private val matchesResultsDAO: MatchesResultsDAO,
    private val matchMapper: Mapper<MatchLocalModel, MatchDTO>,
    private val matchResultMapper: Mapper<MatchResultLocalModel, MatchResultDTO>
) : LocalDataSource {

    override suspend fun addAllMatches(matchesList: List<MatchDTO>): List<Long> {
        val matchLocalModel = matchMapper.toList(matchesList)
        return matchesDAO.addAllMatches(matchLocalModel)
    }

    override suspend fun addAllMatchesResults(matchesResults: List<MatchResultDTO>): List<Long> {
        val matchResultLocalModel = matchResultMapper.toList(matchesResults)
        return matchesResultsDAO.addAllMatchesResults(matchResultLocalModel)
    }

    override suspend fun getAllMatches(): List<MatchDTO> {
        val matchLocalModel = matchesDAO.getAllMatches()
        return matchMapper.fromList(matchLocalModel)
    }

    override suspend fun getAllMatchesResults(): List<MatchResultDTO> {
        val matchLocalModel = matchesResultsDAO.getAllMatchesResults()
        return matchResultMapper.fromList(matchLocalModel)
    }

    override suspend fun deleteAllMatches(): Int {
        return matchesDAO.deleteAllMatches()
    }

    override suspend fun deleteAllMatchesResults(): Int {
        return matchesResultsDAO.deleteAllMatchesResults()
    }
}