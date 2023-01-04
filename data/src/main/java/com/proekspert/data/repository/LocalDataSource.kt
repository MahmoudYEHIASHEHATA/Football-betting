package com.proekspert.data.repository

import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO

/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addMatchBet(matchBet: MatchResultDTO): Long

    suspend fun addAllMatches(matchesList: List<MatchDTO>): List<Long>

    suspend fun addAllMatchesResults(matchesResults: List<MatchResultDTO>): Long

    suspend fun getMatchBet(team1: String, team2: String): MatchResultDTO

    suspend fun getAllMatches(): List<MatchDTO>

    suspend fun getAllMatchesResults(): List<MatchResultDTO>

    suspend fun deleteAllMatchesBet()

    suspend fun deleteAllMatches()

    suspend fun deleteAllMatchesResults()
}