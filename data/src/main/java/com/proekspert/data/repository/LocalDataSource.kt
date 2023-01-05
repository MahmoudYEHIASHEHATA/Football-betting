package com.proekspert.data.repository

import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO

/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addAllMatches(matchesList: List<MatchDTO>): List<Long>

    suspend fun addAllMatchesResults(matchesResults: List<MatchResultDTO>): List<Long>

    suspend fun getAllMatches(): List<MatchDTO>

    suspend fun getAllMatchesResults(): List<MatchResultDTO>

    suspend fun deleteAllMatches() : Int

    suspend fun deleteAllMatchesResults() : Int
}