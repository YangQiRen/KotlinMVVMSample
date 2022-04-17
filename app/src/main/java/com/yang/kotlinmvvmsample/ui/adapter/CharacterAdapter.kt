package com.yang.kotlinmvvmsample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yang.kotlinmvvmsample.data.model.Character
import com.yang.kotlinmvvmsample.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onCharacterClicked: (binding: ItemCharacterBinding, character: Character) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onCharacterClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as Character
                )
            }
        }

        fun bind(item: Character) = with(binding) {
            character = item
        }
    }


    object CharacterComparator : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }
}