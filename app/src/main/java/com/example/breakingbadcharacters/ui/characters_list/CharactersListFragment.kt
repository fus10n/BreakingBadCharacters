package com.example.breakingbadcharacters.ui.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.content.domains.BBCharacter
import com.example.breakingbadcharacters.ui.characters_list.adapter.CharacterViewHolder
import com.example.breakingbadcharacters.ui.characters_list.adapter.CharactersRecyclerAdapter
import com.example.breakingbadcharacters.ui.characters_list.adapter.CharactersRecyclerDecoration
import com.example.breakingbadcharacters.ui.characters_list.adapter.OnCharacterClickListener
import com.example.breakingbadcharacters.ui.main.MainActivity

import kotlinx.android.synthetic.main.fragment_list.*

class CharactersListFragment : Fragment(), OnCharacterClickListener {

    private val listAdapter = CharactersRecyclerAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_list_recycler.layoutManager = LinearLayoutManager(view.context)
        fragment_list_recycler.addItemDecoration(CharactersRecyclerDecoration(view.resources))
        fragment_list_recycler.adapter = listAdapter
    }

    override fun onCharacterClick(viewHolder: CharacterViewHolder) {
        activity?.let { fragmentActivity ->
            val character = listAdapter.getItem(viewHolder.adapterPosition)
            if (character != null && fragmentActivity is MainActivity)
                fragmentActivity.showCharacterDetailsFragment(character, viewHolder.itemView.findViewById(R.id.item_character_image))
        }
    }

    fun setCharacters(characters: MutableList<BBCharacter>) {
        listAdapter.setItems(characters)
    }

}