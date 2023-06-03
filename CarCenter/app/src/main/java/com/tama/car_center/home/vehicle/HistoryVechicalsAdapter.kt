package com.tama.car_center.home.vehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tama.domain.model.VehicleVisited
import com.tama.syarah.databinding.ItemLayoutHistoryBinding

@BindingAdapter("app:rvHistoryItems",
    "app:OnclickItem")
fun bindVechcalVistAdapter(
    recyclerView: RecyclerView,
    list: List<VehicleVisited>,
    clickItem: (VehicleVisited) -> Unit
) {
    list.also {
        val adapter: HistoryVechicalAdapter
        if (recyclerView.adapter == null) {
            adapter = HistoryVechicalAdapter(clickItem)
            recyclerView.adapter = adapter
        } else {
            adapter = recyclerView.adapter as HistoryVechicalAdapter
        }
        adapter.submitList(it)
    }
}


class HistoryVechicalAdapter(private val clickItem: (VehicleVisited) -> Unit) :
    ListAdapter<VehicleVisited, HistoryVechicalAdapter.ViewHolderItem>(SettingItemDiffCallback()) {

    class ViewHolderItem(private val binding: ItemLayoutHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicleVisited: VehicleVisited, clickItem: (VehicleVisited) -> Unit) {
            binding.car = vehicleVisited
            binding.root.setOnClickListener { v -> clickItem.invoke(vehicleVisited) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        return ViewHolderItem(
            ItemLayoutHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        holder.bind(currentList[position], clickItem)
    }


    class SettingItemDiffCallback : DiffUtil.ItemCallback<VehicleVisited>() {
        override fun areItemsTheSame(
            oldItem:VehicleVisited,
            newItem: VehicleVisited
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: VehicleVisited,
            newItem: VehicleVisited
        ) = (oldItem == newItem)
    }
}