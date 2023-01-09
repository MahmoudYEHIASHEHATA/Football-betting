package com.proekspert.local.database

import androidx.room.*
import com.proekspert.local.model.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMatches(authors: List<MatchEntity>): List<Long>

    @Query("SELECT * FROM matches")
    fun getAllMatches(): Flow<List<MatchEntity>>

    @Query("DELETE FROM matches")
    suspend fun deleteAllMatches(): Int

    @Update
    suspend fun updateMatch(match: MatchEntity)

    @Query("SELECT EXISTS (SELECT * FROM matches WHERE bettingScoreTeam1 >= 0 OR bettingScoreTeam2 >=0)")
    suspend fun existsPredictions(): Boolean

    @Query("SELECT (SELECT COUNT(*) FROM matches) == 0")
    fun isEmpty(): Boolean
}