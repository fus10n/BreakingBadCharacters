package com.example.breakingbadcharacters.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater

import com.example.breakingbadcharacters.content.ContentManager
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.content.domains.BBCharacter
import com.example.breakingbadcharacters.ui.character_details.CharacterDetailsFragment
import com.example.breakingbadcharacters.ui.characters_list.CharactersListFragment

import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), ContentManager.FetchCallback {

    private val fragmentContainerId = R.id.main_fragment_container
    private val fragmentsMap = HashMap<KClass<out Fragment>, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragments()
        ContentManager.fetchData(this)
    }

    override fun onDataFetched(characters: MutableList<BBCharacter>) {
        if (characters.isNotEmpty())
            (fragmentsMap[CharactersListFragment::class] as CharactersListFragment)
                .setCharacters(characters)
    }

    fun showCharacterDetailsFragment(character: BBCharacter, transitionView: View) {
        val detailsFragment = fragmentsMap[CharacterDetailsFragment::class] as CharacterDetailsFragment
        detailsFragment.setCharacter(character, transitionView.transitionName)
        supportFragmentManager.beginTransaction()
            .addSharedElement(transitionView, transitionView.transitionName)
            .replace(fragmentContainerId, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupFragments() {
        val listFragment = CharactersListFragment()
        val detailsFragment = CharacterDetailsFragment()

        TransitionInflater.from(this).let {
            val sharedElemTransition = it.inflateTransition(R.transition.shared_element_transition)
            val fragmentTransition = it.inflateTransition(R.transition.fragment_transition)

            listFragment.reenterTransition = fragmentTransition
            detailsFragment.enterTransition = fragmentTransition
            detailsFragment.sharedElementEnterTransition = sharedElemTransition
            detailsFragment.sharedElementReturnTransition = sharedElemTransition
        }

        fragmentsMap[CharactersListFragment::class] = listFragment
        fragmentsMap[CharacterDetailsFragment::class] = detailsFragment

        supportFragmentManager.beginTransaction().add(fragmentContainerId, listFragment).commit()
    }

}