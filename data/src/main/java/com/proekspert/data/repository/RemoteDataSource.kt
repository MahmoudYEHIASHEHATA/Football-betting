package com.proekspert.data.repository

import com.proekspert.data.model.MatchDTO
import com.proekspert.data.model.MatchResultDTO

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

    suspend fun getAllMatches() : List<MatchDTO>

    suspend fun getAllMatchesResults() : List<MatchResultDTO>
}