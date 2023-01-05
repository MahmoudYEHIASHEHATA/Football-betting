package com.proekspert.feature.ui.results


import com.proekspert.base.BaseViewHolder
import com.proekspert.feature.databinding.RowMatchItemLayoutBinding
import com.proekspert.feature.databinding.RowMatchResultItemLayoutBinding
import com.proekspert.feature.model.MatchResultUiModel
import com.proekspert.feature.model.MatchUiModel

/**
 * ViewHolder class for match
 */
class MatchResultViewHolder constructor(
    private val binding : RowMatchResultItemLayoutBinding,
) : BaseViewHolder<MatchResultUiModel, RowMatchResultItemLayoutBinding>(binding) {

    override fun bind() {


        getRowItem()?.let {
            binding.matchResult = it
            binding.executePendingBindings()

        }
    }
}