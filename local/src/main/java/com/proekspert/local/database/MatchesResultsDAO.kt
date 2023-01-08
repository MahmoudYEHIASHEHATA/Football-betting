package com.proekspert.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proekspert.local.model.MatchResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesResultsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMatchesResults(authors : List<MatchResultEntity>): List<Long>

    @Query("SELECT * FROM matches_results")
    fun getAllMatchesResults(): Flow<List<MatchResultEntity>>

    @Query("DELETE FROM matches_results")
    suspend fun deleteAllMatchesResults() : Int

    @Query("SELECT (SELECT COUNT(*) FROM matches_results) == 0")
    fun isEmpty(): Boolean
}