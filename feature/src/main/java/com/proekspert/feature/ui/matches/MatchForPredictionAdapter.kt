package com.proekspert.feature.ui.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.proekspert.base.BaseRecyclerAdapter
import com.proekspert.feature.databinding.RowMatchItemLayoutBinding
import com.proekspert.feature.model.MatchUiModel

/**
 * Adapter class for RecyclerView
 */
class MatchForPredictionAdapter constructor(
    private val clickFunc: ((MatchUiModel?) -> Unit)? = null
) : BaseRecyclerAdapter<MatchUiModel, RowMatchItemLayoutBinding, MatchForPredictionViewHolder>(
    MatchItemDiffUtil()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchForPredictionViewHolder {
        val binding = RowMatchItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return MatchForPredictionViewHolder(binding = binding, click = clickFunc)

    }

}

class MatchItemDiffUtil : DiffUtil.ItemCallback<MatchUiModel>() {
    override fun areItemsTheSame(oldItem: MatchUiModel, newItem: MatchUiModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MatchUiModel, newItem: MatchUiModel): Boolean {
        return oldItem == newItem
    }
}