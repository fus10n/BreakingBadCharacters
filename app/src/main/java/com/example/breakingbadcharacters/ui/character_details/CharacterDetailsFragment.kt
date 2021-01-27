package com.example.breakingbadcharacters.ui.character_details

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.content.domains.BBCharacter

import kotlinx.android.synthetic.main.fragment_details.*

class CharacterDetailsFragment : Fragment() {

    private var bbCharacter: BBCharacter? = null
    private var transitionName: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        displayCharacter()
    }

    fun setCharacter(character: BBCharacter, transitionName: String) {
        if (character != bbCharacter)
            bbCharacter = character

        if (transitionName != this.transitionName)
            this.transitionName = transitionName

        if (view != null)
            displayCharacter()
    }

    @SuppressLint("SetTextI18n")
    private fun displayCharacter() {
        character_detail_image.transitionName = transitionName

        bbCharacter?.let { character ->
            character_detail_name.text = "${character.name} \"${character.nickname}\""
            character_detail_occupation.text = getString(R.string.character_occupation) + " - " + character.occupation.joinToString { it }
            character_detail_status.text = getString(R.string.character_status) + " - " + character.status
            character_detail_seasons.text = getString(R.string.character_seasons) + " - " + character.appearance.joinToString { it.toString() }

            Glide.with(character_detail_image)
                .load(character.imageUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(character_detail_image)
        }
    }

}