package com.proekspert.feature.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.proekspert.base.BaseFragment
import com.proekspert.feature.R
import com.proekspert.feature.contract.MatchesForPredictionContract
import com.proekspert.feature.core.showErrorDialog
import com.proekspert.feature.databinding.FragmentMatchesForPredictionBinding
import com.proekspert.feature.model.MatchUiModel
import com.proekspert.feature.ui.betting.BettingDialogFragmentArgs
import com.proekspert.feature.ui.vm.MatchesForPredictionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * Matches For Prediction Fragment
 */
@AndroidEntryPoint
class MatchesForPredictionFragment : BaseFragment<FragmentMatchesForPredictionBinding>() {

    private val viewModel: MatchesForPredictionViewModel by viewModels()
    private val adapter: MatchForPredictionAdapter by lazy {
        MatchForPredictionAdapter {
            openBettingDialog(it)
        }
    }

    private fun openBettingDialog(it: MatchUiModel?) {
        findNavController().navigate(
            R.id.bettingDialogFragment,
            BettingDialogFragmentArgs(it).toBundle()
        )
    }

    private fun openMatchesResultsFragment() {
        val action =
            MatchesForPredictionFragmentDirections.actionMatchesForPredictionFragmentToMatchesResultsFragment()
        findNavController().navigate(action)
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMatchesForPredictionBinding
        get() = FragmentMatchesForPredictionBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.setEvent(MatchesForPredictionContract.Event.OnFetchAllMatchesForPrediction)
        binding.rvMatchesForPrediction.adapter = adapter
        binding.getResultsBtn.setOnClickListener { viewModel.setEvent(MatchesForPredictionContract.Event.ShowAllResults) }
        initObservers()
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.matchState) {
                        is MatchesForPredictionContract.MatchState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is MatchesForPredictionContract.MatchState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is MatchesForPredictionContract.MatchState.Success -> {
                            val data = state.matchesList
                            adapter.submitList(data)
                            binding.loadingPb.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MatchesForPredictionContract.Effect.ShowError -> {
                            val msg = it.message
                            msg?.let { message ->
                                context?.showErrorDialog(message) {
                                    viewModel.setEvent(
                                        MatchesForPredictionContract.Event.OnFetchAllMatchesForPrediction
                                    )
                                }
                            }
                        }
                        is MatchesForPredictionContract.Effect.NavigateToPrediction -> {
                            openMatchesResultsFragment()
                        }
                        is MatchesForPredictionContract.Effect.NoPrediction -> {
                            Snackbar.make(
                                binding.root,
                                "No prediction exist pleas enter prediction to show the results",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}