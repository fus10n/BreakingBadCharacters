package com.example.breakingbadcharacters.ui.characters_list.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.content.domains.BBCharacter

class CharacterViewHolder(itemView: View, clickListener: OnCharacterClickListener) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { clickListener.onCharacterClick(this) }
    }

    fun bind(character: BBCharacter) {
        itemView.findViewById<TextView>(R.id.item_character_name).text = character.name
        itemView.findViewById<ImageView>(R.id.item_character_image).apply {
            transitionName = context.getString(R.string.image_transition_name, adapterPosition)
            Glide.with(this).load(character.imageUrl).into(this)
        }
    }

}