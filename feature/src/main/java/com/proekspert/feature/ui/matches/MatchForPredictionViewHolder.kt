package com.proekspert.feature.ui.matches


import com.proekspert.base.BaseViewHolder
import com.proekspert.feature.databinding.RowMatchItemLayoutBinding
import com.proekspert.feature.model.MatchUiModel

/**
 * ViewHolder class for match
 */
class MatchForPredictionViewHolder constructor(
    private val binding : RowMatchItemLayoutBinding,
    private val click : ((MatchUiModel?) -> Unit)? = null
) : BaseViewHolder<MatchUiModel, RowMatchItemLayoutBinding>(binding) {


    init {
        binding.crdMatch.setOnClickListener {
            click?.invoke(getRowItem())
        }
    }

    override fun bind() {


        getRowItem()?.let {
            binding.match = it
            binding.executePendingBindings()

        }
    }
}