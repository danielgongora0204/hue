package com.gig.hue.com.gig.hue.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gig.hue.R
import com.gig.hue.models.temp.RentItemTemp
import com.gig.hue.databinding.ItemSearchRentBinding

class SearchLocationAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = emptyList<Any>()

    private enum class Type(val value: Int) {
        RENT(1);

        companion object {
            fun getEnum(value: Int): Type = when(value) {
                1 -> RENT
                else -> RENT
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  =
       when (Type.getEnum(viewType)) {
            Type.RENT -> RentSearchLocationViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_rent,
                    parent,
        false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(Type.getEnum(getItemViewType(position))) {
            Type.RENT -> (holder as RentSearchLocationViewHolder).bindData(data[position] as RentItemTemp)
        }

    override fun getItemCount(): Int = data.size;

    override fun getItemViewType(position: Int): Int {
        return Type.RENT.value
    }
}

class RentSearchLocationViewHolder(private val binding: ItemSearchRentBinding): RecyclerView.ViewHolder(binding.root){

    fun bindData(data: RentItemTemp) {
        binding.rentItemTemp = data;
    }
}