package com.proekspert.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth
import com.proekspert.local.database.AppDatabase
import com.proekspert.local.database.MatchesDAO
import com.proekspert.local.database.MatchesResultsDAO
import com.proekspert.local.utils.TestDataGenerator
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MatchDAOTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: AppDatabase
    private lateinit var matchesDao: MatchesDAO
    private lateinit var matchesResultsDAO: MatchesResultsDAO

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        matchesDao = database.matchesDao()
        matchesResultsDAO = database.matchesResultsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun test_add_and_get_matches_items_success() = run {
        runBlocking {

            val item = TestDataGenerator.generateMatchItems()

            matchesDao.addAllMatches(item)

            val result = matchesDao.getAllMatches()

            Truth.assertThat(result.first()).isEqualTo(item)
        }
    }

    @Test
    fun test_add_and_get_matchesResults_items_success() = run {
        runBlocking {

            val item = TestDataGenerator.generateMatchResultItems()

            matchesResultsDAO.addAllMatchesResults(item)

            val result = matchesResultsDAO.getAllMatchesResults()

            Truth.assertThat(result.first()).isEqualTo(item)
        }
    }
}