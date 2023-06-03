package com.tama.car_center.home.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tama.syarah.databinding.ItemSettingLayoutBinding


@BindingAdapter("app:rvSettingsItems",
        "app:OnclickItem")
fun setSettingsItemslist(
    recyclerView: RecyclerView,
    list: List<SettingsItem>,
    clickItem: ( SettingsItem) -> Unit
) {
    list.also {
        val adapter: SettingAdapterData
        if (recyclerView.adapter == null) {
            adapter = SettingAdapterData(clickItem)
            recyclerView.adapter = adapter
        } else {
            adapter = recyclerView.adapter as SettingAdapterData
        }
        adapter.submitList(it)
    }
}

class SettingAdapterData(private val clickItem: (SettingsItem) -> Unit) :
    ListAdapter<SettingsItem, SettingAdapterData.ViewHolderItemSetting>(SettingItemDiffCallback()) {

    class ViewHolderItemSetting(private val binding: ItemSettingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(settingsItem: SettingsItem, clickItem: (SettingsItem) -> Unit) {
            binding.model = settingsItem
            binding.root.setOnClickListener { v -> clickItem.invoke(settingsItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemSetting {
        return ViewHolderItemSetting(
            ItemSettingLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemSetting, position: Int) {
        holder.bind(currentList[position], clickItem)
    }


    class SettingItemDiffCallback : DiffUtil.ItemCallback<SettingsItem>() {
        override fun areItemsTheSame(
            oldItem: SettingsItem,
            newItem: SettingsItem
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: SettingsItem,
            newItem: SettingsItem
        ) = (oldItem == newItem)
    }
}