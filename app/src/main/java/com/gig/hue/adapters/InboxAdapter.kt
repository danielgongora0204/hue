package com.gig.hue.com.gig.hue.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gig.hue.R
import com.gig.hue.databinding.ItemConversationBinding
import com.gig.hue.models.temp.ConversationItemTemp
import com.squareup.picasso.Picasso


class InboxAdapter(var data: List<Any> = emptyList(), private val listener: (Any) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class Type(val value: Int) {
        NORMAL(1);

        companion object {
            fun getEnum(value: Int): Type = when(value) {
                1 -> NORMAL
                else -> NORMAL
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (Type.getEnum(viewType)) {
            Type.NORMAL -> NormalConversationViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_conversation,
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(Type.getEnum(getItemViewType(position))) {
            Type.NORMAL -> { (holder as InboxAdapter.NormalConversationViewHolder).bindData(data[position] as ConversationItemTemp, listener)}
        }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return Type.NORMAL.value
    }

    inner class NormalConversationViewHolder(private val binding: ItemConversationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ConversationItemTemp, listener: (Any) -> Unit) {
            binding.root.setOnClickListener { listener(data) }
            binding.conversationItemTemp = data
            Picasso.get()
                .load("https://i.imgur.com/tGbaZCY.jpg")
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .fit()
                .into(binding.inboxItemImageView);
        }
    }
}