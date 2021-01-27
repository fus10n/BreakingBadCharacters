package com.example.breakingbadcharacters.content.domains

import com.google.gson.annotations.SerializedName

class CharactersJsonArray(
    @SerializedName(MAIN_ARRAY_PARAM) val charactersArray: Array<BBCharacter>
) {

    companion object {
        const val MAIN_ARRAY_PARAM = "main_array_parameter"
    }

}