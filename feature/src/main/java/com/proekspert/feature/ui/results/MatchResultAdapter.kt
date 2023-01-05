package com.proekspert.feature.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.proekspert.base.BaseRecyclerAdapter
import com.proekspert.feature.databinding.RowMatchResultItemLayoutBinding
import com.proekspert.feature.model.MatchResultUiModel

/**
 * Adapter class for RecyclerView
 */
class MatchResultAdapter constructor() :
    BaseRecyclerAdapter<MatchResultUiModel, RowMatchResultItemLayoutBinding, MatchResultViewHolder>(
        MatchResultItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchResultViewHolder {
        val binding = RowMatchResultItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return MatchResultViewHolder(binding = binding)

    }

}

class MatchResultItemDiffUtil : DiffUtil.ItemCallback<MatchResultUiModel>() {
    override fun areItemsTheSame(
        oldItem: MatchResultUiModel,
        newItem: MatchResultUiModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MatchResultUiModel,
        newItem: MatchResultUiModel
    ): Boolean {
        return oldItem == newItem
    }
}