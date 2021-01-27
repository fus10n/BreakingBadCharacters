package com.example.breakingbadcharacters.ui.characters_list.adapter

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.example.breakingbadcharacters.R

class CharactersRecyclerDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    private val verticalSpacing = resources.getDimensionPixelSize(R.dimen.characters_list_padding)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpacing
    }

}