package com.proekspert.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proekspert.local.model.MatchLocalModel

@Dao
interface MatchesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMatches(authors : List<MatchLocalModel>): List<Long>

    @Query("SELECT * FROM matches")
    suspend fun getAllMatches(): List<MatchLocalModel>

    @Query("DELETE FROM matches")
    suspend fun deleteAllMatches() : Int
}