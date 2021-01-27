package com.example.breakingbadcharacters.ui.characters_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.content.domains.BBCharacter

class CharactersRecyclerAdapter(private val clickListener: OnCharacterClickListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    private val itemsList = mutableListOf<BBCharacter>()

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        if (!::layoutInflater.isInitialized)
            layoutInflater = LayoutInflater.from(parent.context)

        return CharacterViewHolder(
            layoutInflater.inflate(R.layout.item_character, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(itemsList[position])

    override fun getItemCount(): Int = itemsList.size

    fun getItem(position: Int): BBCharacter? {
        if (position in 0 until itemCount)
            return itemsList[position]

        return null
    }

    fun setItems(newItems: MutableList<BBCharacter>) {
        if (itemCount > 0)
            itemsList.clear()

        if (newItems.isNotEmpty())
            itemsList.addAll(newItems)

        notifyDataSetChanged()
    }

}