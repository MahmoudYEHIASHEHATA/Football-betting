package com.proekspert.domain.repository

import com.proekspert.domain.model.Match
import com.proekspert.domain.model.MatchResult
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {
    suspend fun getFreshMatches(): Flow<List<Match>>

    suspend fun getCachedMatches(): Flow<List<Match>>

    suspend fun editMatch(match: Match)

    suspend fun deleteAllMatches(): Int

    suspend fun deleteAllMatchesResults(): Int

    suspend fun getMatchesResults(): Flow<List<MatchResult>>

    suspend fun existsPredictions(): Boolean

}