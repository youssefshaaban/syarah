package com.tama.driver.home.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tama.domain.model.CarService
import com.tama.driver.databinding.ItemLayoutMapBinding

internal class CarServiceViewPagerAdapter(
        private val carServiceSelectionListener: ((CarService) -> Unit)?
) : ListAdapter<CarService,CarServiceViewPagerAdapter.ViewHolder>(CareServiceItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                ItemLayoutMapBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(currentList[position], carServiceSelectionListener)
    }




    class ViewHolder(private val binding: ItemLayoutMapBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bindItem(carService: CarService, careServiceSelectionListener: ((CarService) -> Unit)?) {

        }
    }

    class CareServiceItemDiffCallback : DiffUtil.ItemCallback<CarService>() {
        override fun areItemsTheSame(
            oldItem: CarService,
            newItem: CarService
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CarService,
            newItem: CarService
        ) = oldItem==newItem
    }
}
