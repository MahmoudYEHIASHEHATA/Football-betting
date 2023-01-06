package com.proekspert.betting.di

import android.content.Context
import androidx.room.Room
import com.proekspert.local.database.AppDatabase
import com.proekspert.local.database.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that holds database related classes
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    /**
     * Provides [AppDatabase] instance
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DatabaseConstants.databaseName)
            .build()
    }

    /**
     * Provides [MatchesDAO] instance
     */
    @Provides
    @Singleton
    fun provideMatchesDAO(appDatabase: AppDatabase) = appDatabase.matchesDao()



    /**
     * Provides [MatchesResultsDAO] instance
     */
    @Provides
    @Singleton
    fun provideMatchesResultsDAO(appDatabase: AppDatabase) = appDatabase.matchesResultsDao()

}