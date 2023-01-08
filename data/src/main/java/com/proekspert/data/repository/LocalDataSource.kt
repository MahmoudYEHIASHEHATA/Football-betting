package com.proekspert.data.repository

import com.proekspert.data.model.MatchModel
import com.proekspert.data.model.MatchResultModel
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addAllMatches(matchesList: List<MatchModel>): List<Long>

    suspend fun editMatch(match: MatchModel)

    suspend fun addAllMatchesResults(matchesResults: List<MatchResultModel>): List<Long>

    suspend fun getAllMatches(): Flow<List<MatchModel>>

    suspend fun getAllMatchesResults(): Flow<List<MatchResultModel>>

    suspend fun deleteAllMatches() : Int

    suspend fun deleteAllMatchesResults() : Int

    suspend fun existsPredictions(): Boolean

    suspend fun isMatchesTableEmpty(): Boolean

}