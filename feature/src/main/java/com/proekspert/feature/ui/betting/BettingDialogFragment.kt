package com.proekspert.feature.ui.betting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.proekspert.base.BaseDialogFragment
import com.proekspert.feature.R
import com.proekspert.feature.contract.BettingContract
import com.proekspert.feature.databinding.BettingDialogueLayoutBinding
import com.proekspert.feature.ui.vm.BettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BettingDialogFragment : BaseDialogFragment() {

    private val args: BettingDialogFragmentArgs by navArgs()
    private val viewModel: BettingViewModel by viewModels()

    private lateinit var dataBinding: BettingDialogueLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.betting_dialogue_layout, container,
            false
        )

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setEvent(BettingContract.Event.SetMatch(args.match))
        handleActionClick()
        initObservers()
    }

    private fun handleActionClick() {
        dataBinding.increaseTeam1Btn.setOnClickListener { viewModel.setEvent(BettingContract.Event.AddScoreTeam1) }
        dataBinding.increaseTeam2Btn.setOnClickListener { viewModel.setEvent(BettingContract.Event.AddScoreTeam2) }
        dataBinding.minusTeam1Btn.setOnClickListener { viewModel.setEvent(BettingContract.Event.MinusScoreTeam1) }
        dataBinding.minusTeam2Btn.setOnClickListener { viewModel.setEvent(BettingContract.Event.MinusScoreTeam2) }
        dataBinding.saveBtn.setOnClickListener {
            viewModel.setEvent(
                BettingContract.Event.UpdateMatch(
                    viewModel.currentState.matchState
                )
            )
        }
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    dataBinding.match = it.matchState
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is BettingContract.Effect.DismissDialog -> {
                            dismiss()
                        }
                    }
                }
            }
        }
    }
}