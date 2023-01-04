package com.proekspert.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.proekspert.local.model.MatchLocalModel
import com.proekspert.local.model.MatchResultLocalModel

@Database(
    entities = [
        MatchLocalModel::class,
        MatchResultLocalModel::class
    ],
    version = DatabaseConstants.databaseVersion,
    // Enable export database schema to allow $[androidx.room.AutoMigration] in the next database versions
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchesDao(): MatchesDAO
    abstract fun matchesResultsDao(): MatchesResultsDAO
}