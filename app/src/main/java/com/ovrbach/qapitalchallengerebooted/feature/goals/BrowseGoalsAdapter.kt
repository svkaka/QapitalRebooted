package com.ovrbach.qapitalchallengerebooted.feature.goals

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ovrbach.qapitalchallengerebooted.R
import com.ovrbach.qapitalchallengerebooted.databinding.ItemGoalBinding
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.helper.ItemClickListener
import com.ovrbach.qapitalchallengerebooted.helper.bind
import javax.inject.Inject

class BrowseGoalsAdapter @Inject constructor() :
    ListAdapter<Goal, BrowseGoalsAdapter.Holder>(
        object : DiffUtil.ItemCallback<Goal>() {

            override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean =
                oldItem == newItem
        }
    ) {

    var itemClickListener: ItemClickListener<Goal>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            parent.bind(R.layout.item_goal)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    inner class Holder(val binding: ItemGoalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Goal) {
            binding.root.setOnClickListener { itemClickListener?.onItemClicked(item) }
            binding.item = item
            binding.executePendingBindings()
        }
    }

}