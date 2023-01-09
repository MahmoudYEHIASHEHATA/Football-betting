package com.proekspert.betting.di

import android.content.Context
import com.proekspert.base.AppPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): AppPreference {
        return AppPreference(context.getSharedPreferences("PREFERENCES_NAME", Context.MODE_PRIVATE))
    }
}