package com.proekspert.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.proekspert.base.BaseViewModel
import com.proekspert.common.Mapper
import com.proekspert.domain.model.Match
import com.proekspert.domain.usecase.GetMatchesUseCase
import com.proekspert.feature.contract.MatchesForPredictionContract
import com.proekspert.feature.model.MatchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesForPredictionViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase,
    private val matchMapper: Mapper<Match, MatchUiModel>
) : BaseViewModel<MatchesForPredictionContract.Event, MatchesForPredictionContract.State, MatchesForPredictionContract.Effect>() {

    override fun createInitialState(): MatchesForPredictionContract.State {
        return MatchesForPredictionContract.State(
            matchState = MatchesForPredictionContract.MatchState.Idle,
            selectedMatch = null
        )
    }

    override fun handleEvent(event: MatchesForPredictionContract.Event) {
        when (event) {
            is MatchesForPredictionContract.Event.OnFetchAllMatchesForPrediction -> {
                getAllMatches()
            }
            is MatchesForPredictionContract.Event.OnMatchItemClicked -> {
                val item = event.match
                setSelectedMatch(match = item)
            }
        }
    }

    /**
     * Fetch matches
     */
    private fun getAllMatches() {
        viewModelScope.launch {
            getMatchesUseCase.execute(null)
                .onStart { setState { copy(matchState = MatchesForPredictionContract.MatchState.Loading) } }
                .catch { setEffect { MatchesForPredictionContract.Effect.ShowError(message = it.message) } }
                .collect {
                    // Set State
                    val matchesList = matchMapper.fromList(it)
                    setState {
                        copy(
                            matchState = MatchesForPredictionContract.MatchState.Success(
                                matchesList = matchesList
                            )
                        )
                    }
                }
        }
    }

    /**
     * Set selected match item
     */
    private fun setSelectedMatch(match: MatchUiModel?) {
        // Set State
        setState { copy(selectedMatch = match) }
    }
}