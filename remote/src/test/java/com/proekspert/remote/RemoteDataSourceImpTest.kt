package com.proekspert.remote

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.proekspert.data.repository.RemoteDataSource
import com.proekspert.remote.api.ApiService
import com.proekspert.remote.mapper.MatchNetworkDataMapper
import com.proekspert.remote.mapper.MatchResultNetworkDataMapper
import com.proekspert.remote.source.RemoteDataSourceImp
import com.proekspert.remote.utils.MainCoroutineRule
import com.proekspert.remote.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var apiService: ApiService
    private val matchMapper = MatchNetworkDataMapper()
    private val matchResultMapper = MatchResultNetworkDataMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService,
            matchesMapper = matchMapper,
            matchesResultsMapper = matchResultMapper,
            dispatcher = mainCoroutineRule.dispatcher
        )
    }


    @Test
    fun test_get_Matches_success() = run {
        runBlocking {
            val matchNetwork = TestDataGenerator.generateMatches()

            // Given
            coEvery { apiService.getMatches() } returns matchNetwork

            // When
            val result = remoteDataSource.getAllMatches()

            // Then
            coVerify { apiService.getMatches() }

            // Assertion
            val expected = matchMapper.fromList(matchNetwork.matches)
            Truth.assertThat(result).isEqualTo(expected)
        }
    }

    @Test(expected = Exception::class)
    fun test_get_Matches_fail() = run {
        runBlocking {

            // Given
            coEvery { apiService.getMatches() } throws Exception()

            // When
            remoteDataSource.getAllMatches()

            // Then
            coVerify { apiService.getMatches() }

        }
    }

    @Test
    fun test_get_MatchesResults_success() = run {
        runBlocking {

            val matchesResultsNetwork = TestDataGenerator.generateMatchResults()

            // Given
            coEvery { apiService.getMatchesResults() } returns matchesResultsNetwork

            // When
            val result = remoteDataSource.getAllMatchesResults()

            // Then
            coVerify { apiService.getMatchesResults() }

            // Assertion
            val expected = matchResultMapper.fromList(matchesResultsNetwork.matches)
            Truth.assertThat(result).isEqualTo(expected)
        }
    }

    @Test(expected = Exception::class)
    fun test_get_AuthorPosts_fail() = run{
        runBlocking {

            // Given
            coEvery { apiService.getMatchesResults() } throws Exception()

            // When
            remoteDataSource.getAllMatchesResults()

            // Then
            coVerify { apiService.getMatchesResults() }

        }
    }
}