package com.proekspert.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.proekspert.base.BaseViewModel
import com.proekspert.common.Mapper
import com.proekspert.domain.model.MatchResultsWithPrediction
import com.proekspert.domain.usecase.GetMatchesResultsWithPredictionUseCase
import com.proekspert.feature.contract.MatchesResultsContract
import com.proekspert.feature.model.MatchResultUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesResultsViewModel @Inject constructor(
    private val getMatchesResultsWithPredictionUseCase: GetMatchesResultsWithPredictionUseCase,
    private val matchMapper: Mapper<MatchResultsWithPrediction, MatchResultUiModel>
) : BaseViewModel<MatchesResultsContract.Event, MatchesResultsContract.State, MatchesResultsContract.Effect>() {

    override fun createInitialState(): MatchesResultsContract.State {
        return MatchesResultsContract.State(
            matchResultState = MatchesResultsContract.MatchResultState.Idle,
        )
    }

    override fun handleEvent(event: MatchesResultsContract.Event) {
        when (event) {
            is MatchesResultsContract.Event.OnFetchAllMatchesResults -> {
                getAllMatchesResults()
            }
        }
    }

    /**
     * Fetch matches results
     */
    private fun getAllMatchesResults() {
        viewModelScope.launch {
            getMatchesResultsWithPredictionUseCase.execute(null)
                .onStart { setState { copy(matchResultState = MatchesResultsContract.MatchResultState.Loading) } }
                .catch { setEffect { MatchesResultsContract.Effect.ShowError(message = it.message) } }
                .collect {
                    // Set State
                    val matchesList = matchMapper.fromList(it)
                    setState {
                        copy(
                            matchResultState = MatchesResultsContract.MatchResultState.Success(
                                matchesList = matchesList
                            )
                        )
                    }
                }
        }
    }
}
