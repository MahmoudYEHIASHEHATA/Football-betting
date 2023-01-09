package com.proekspert.feature

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.proekspert.domain.usecase.CheckExistsPredictionsInLocalUseCase
import com.proekspert.domain.usecase.GetMatchesUseCase
import com.proekspert.feature.contract.MatchesForPredictionContract
import com.proekspert.feature.mapper.MatchDomainUiMapper
import com.proekspert.feature.ui.vm.MatchesForPredictionViewModel
import com.proekspert.feature.utils.MainCoroutineRule
import com.proekspert.feature.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MatchesForPredictionViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getMatchesUseCase: GetMatchesUseCase

    @MockK
    private lateinit var checkExistsPredictionsInLocalUseCase: CheckExistsPredictionsInLocalUseCase

    private val matchMapper = MatchDomainUiMapper()

    private lateinit var matchViewModel: MatchesForPredictionViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        matchViewModel = MatchesForPredictionViewModel(
            getMatchesUseCase = getMatchesUseCase,
            matchMapper = matchMapper,
            checkExistsRecordInMatchesLocalUseCase= checkExistsPredictionsInLocalUseCase
        )
    }

    @Test
    fun test_fetch_matchesItems_success() = runBlockingTest {

        val authorsItems = TestDataGenerator.generateMatchesItems()
        val authorsFlow = flowOf(authorsItems)

        // Given
        coEvery { getMatchesUseCase.execute(null) } returns authorsFlow

        // When && Assertions
        matchViewModel.uiState.test {
            matchViewModel.setEvent(MatchesForPredictionContract.Event.OnFetchAllMatchesForPrediction)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MatchesForPredictionContract.State(
                    matchState = MatchesForPredictionContract.MatchState.Idle,
                    selectedMatch  = null
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                MatchesForPredictionContract.State(
                    matchState = MatchesForPredictionContract.MatchState.Loading,
                    selectedMatch = null
                )
            )
            // Expect Success
            val expected = expectItem()
            val expectedData =
                (expected.matchState as MatchesForPredictionContract.MatchState.Success).matchesList
            Truth.assertThat(expected).isEqualTo(
                MatchesForPredictionContract.State(
                    matchState = MatchesForPredictionContract.MatchState.Success(
                            matchMapper.fromList(
                                authorsItems
                            )
                    ),
                    selectedMatch = null
                )
            )
            Truth.assertThat(expectedData)
                .containsExactlyElementsIn(matchMapper.fromList(authorsItems))


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getMatchesUseCase.execute(null) }
    }
}