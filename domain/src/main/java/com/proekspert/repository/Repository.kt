package com.proekspert.repository

import com.proekspert.common.Resource
import com.proekspert.domain.entity.MatchEntity
import com.proekspert.domain.entity.MatchResultEntity
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {
    suspend fun getMatches(): Flow<Resource<List<MatchEntity>>>

    suspend fun getMatchesResults(): Flow<Resource<List<MatchResultEntity>>>
}