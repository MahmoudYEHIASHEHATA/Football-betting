package com.proekspert.data.repository

import com.proekspert.data.model.MatchModel
import com.proekspert.data.model.MatchResultModel

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {

    suspend fun getAllMatches() : List<MatchModel>

    suspend fun getAllMatchesResults() : List<MatchResultModel>
}