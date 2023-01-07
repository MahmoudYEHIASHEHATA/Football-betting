package com.proekspert.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.proekspert.base.BaseViewModel
import com.proekspert.common.Mapper
import com.proekspert.domain.model.Match
import com.proekspert.domain.usecase.UpdateMatchesUseCase
import com.proekspert.feature.contract.BettingContract
import com.proekspert.feature.model.MatchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BettingViewModel @Inject constructor(
    private val updateMatchesUseCase: UpdateMatchesUseCase,
    private val matchMapper: Mapper<Match, MatchUiModel>
) : BaseViewModel<BettingContract.Event, BettingContract.State, BettingContract.Effect>() {


    override fun createInitialState(): BettingContract.State {
        return BettingContract.State(
            matchState = null
        )
    }


    override fun handleEvent(event: BettingContract.Event) {
        when (event) {
            is BettingContract.Event.AddScoreTeam1 -> {
                addScoreReam1()
            }
            is BettingContract.Event.AddScoreTeam2 -> {
                addScoreTeam2()
            }
            is BettingContract.Event.MinusScoreTeam1 -> {
                minusScoreTeam1()
            }
            is BettingContract.Event.MinusScoreTeam2 -> {
                minusScoreTeam2()
            }
            is BettingContract.Event.UpdateMatch -> {
                val item = event.match
                updateMatch(match = item)
            }
            is BettingContract.Event.SetMatch -> {
                val item = event.match
                setState { copy(matchState = item) }
            }
        }
    }

    private fun updateMatch(match: MatchUiModel?) {
        viewModelScope.launch {
            updateMatchesUseCase.updateMatch(matchMapper.to(match))
            setEffect { BettingContract.Effect.DismissDialog }
        }
    }

    private fun addScoreReam1() {
        setState {
            copy(
                matchState = matchState?.copy(
                    bettingScoreTeam1 = matchState.bettingScoreTeam1?.plus(
                        1
                    ) ?: 1
                )
            )
        }
    }

    private fun addScoreTeam2() {
        setState {
            copy(
                matchState = matchState?.copy(
                    bettingScoreTeam2 = matchState.bettingScoreTeam2?.plus(
                        1
                    ) ?: 1
                )
            )
        }
    }

    private fun minusScoreTeam1() {
        setState {
            copy(
                matchState = matchState?.copy(bettingScoreTeam1 = matchState.bettingScoreTeam1?.let {
                    if (it > 0) it.minus(
                        1
                    ) else 0
                } ?: 0)
            )
        }
    }

    private fun minusScoreTeam2() {
        setState {
            copy(
                matchState = matchState?.copy(bettingScoreTeam2 = matchState.bettingScoreTeam2?.let {
                    if (it > 0) it.minus(
                        1
                    ) else 0
                } ?: 0)
            )
        }
    }
}