package com.proekspert.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.proekspert.data.repository.LocalDataSource
import com.proekspert.local.database.MatchesDAO
import com.proekspert.local.database.MatchesResultsDAO
import com.proekspert.local.mapper.MatchesLocalDataMapper
import com.proekspert.local.mapper.MatchesResultLocalDataMapper
import com.proekspert.local.sourc.LocalDataSourceImp
import com.proekspert.local.utils.MainCoroutineRule
import com.shahry.local.utils.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class LocalDataSourceImpTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var matchesDAO: MatchesDAO

    @MockK
    private lateinit var matchesResultsDAO: MatchesResultsDAO

    private val matchMapper = MatchesLocalDataMapper()
    private val matchResultMapper = MatchesResultLocalDataMapper()

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create LocalDataSourceImp before every test
        localDataSource = LocalDataSourceImp(
            matchesDAO = matchesDAO,
            matchesResultsDAO = matchesResultsDAO,
            matchMapper = matchMapper,
            matchResultMapper = matchResultMapper,
            dispatcher = mainCoroutineRule.dispatcher
        )
    }

    @Test
    fun test_add_matches_items_success() = run {
        runBlocking {

            val matchesItems = matchMapper.fromList(list = TestData.generateMatchItems())
            val expected = MutableList(matchesItems.size) { index -> index.toLong() }

            // Given
            coEvery { matchesDAO.addAllMatches(any()) } returns expected

            // When
            val returned = localDataSource.addAllMatches(matchesItems)

            // Then
            coVerify { matchesDAO.addAllMatches(any()) }

            // Assertion
            Truth.assertThat(returned).hasSize(expected.size)
        }
    }

//
    @Test
    fun test_get_matches_items_success() = run {
        runBlocking {

            val matchItems = TestData.generateMatchItems()
            val expected = matchMapper.fromList(list = matchItems)

            // Given
            coEvery { matchesDAO.getAllMatches() } returns flowOf(matchItems)

            // When
            val returned = localDataSource.getAllMatches()

            // Then
            coVerify { matchesDAO.getAllMatches() }

            // Assertion
            Truth.assertThat(returned.first()).isEqualTo(expected)
        }
    }

    @Test
    fun test_add_matches_results_items_success() = run {
        runBlocking {

            val matchesResultsItems = matchResultMapper.fromList(list = TestData.generateMatchResultItems())
            val expected = MutableList(matchesResultsItems.size) { index -> index.toLong() }

            // Given
            coEvery { matchesResultsDAO.addAllMatchesResults(any()) } returns expected

            // When
            val returned = localDataSource.addAllMatchesResults(matchesResultsItems)

            // Then
            coVerify { matchesResultsDAO.addAllMatchesResults(any()) }

            // Assertion
            Truth.assertThat(returned).hasSize(expected.size)
        }
    }

    //
    @Test
    fun test_get_matches_results_items_success() = run {
        runBlocking {

            val matchResultItems = TestData.generateMatchResultItems()
            val expected = matchResultMapper.fromList(list = matchResultItems)

            // Given
            coEvery { matchesResultsDAO.getAllMatchesResults() } returns flowOf(matchResultItems)

            // When
            val returned = localDataSource.getAllMatchesResults()

            // Then
            coVerify { matchesResultsDAO.getAllMatchesResults() }

            // Assertion
            Truth.assertThat(returned.first()).isEqualTo(expected)
        }
    }

}