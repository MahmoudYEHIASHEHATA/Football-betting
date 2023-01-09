package com.proekspert.feature.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.proekspert.base.BaseFragment
import com.proekspert.feature.contract.MatchesResultsContract
import com.proekspert.feature.core.showErrorDialog
import com.proekspert.feature.databinding.FragmentMatchesResultsBinding
import com.proekspert.feature.ui.vm.MatchesResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * Matches Results Fragment
 */
@AndroidEntryPoint
class MatchesResultsFragment : BaseFragment<FragmentMatchesResultsBinding>() {

    private val viewModel: MatchesResultsViewModel by viewModels()
    private val adapter: MatchResultAdapter by lazy {
        MatchResultAdapter()
    }


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMatchesResultsBinding
        get() = FragmentMatchesResultsBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvMatchesResults.adapter = adapter
        binding.restartBtn.setOnClickListener { viewModel.setEvent(MatchesResultsContract.Event.Restart) }
        viewModel.setEvent(MatchesResultsContract.Event.OnFetchAllMatchesResults)
        initObservers()
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.matchResultState) {
                        is MatchesResultsContract.MatchResultState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is MatchesResultsContract.MatchResultState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is MatchesResultsContract.MatchResultState.Success -> {
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
                        is MatchesResultsContract.Effect.ShowError -> {
                            val msg = it.message
                            msg?.let { message ->
                                context?.showErrorDialog(message) {
                                    viewModel.setEvent(
                                        MatchesResultsContract.Event.OnFetchAllMatchesResults
                                    )
                                }
                            }
                        }
                        is MatchesResultsContract.Effect.PopUpResultFragment ->{
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
    }
}