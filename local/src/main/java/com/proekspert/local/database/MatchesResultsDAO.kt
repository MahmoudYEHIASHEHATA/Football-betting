package com.proekspert.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proekspert.local.model.MatchResultLocalModel

@Dao
interface MatchesResultsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMatchesResults(authors : List<MatchResultLocalModel>): List<Long>

    @Query("SELECT * FROM matches_results")
    suspend fun getAllMatchesResults(): List<MatchResultLocalModel>

    @Query("DELETE FROM matches_results")
    suspend fun deleteAllMatchesResults()
}