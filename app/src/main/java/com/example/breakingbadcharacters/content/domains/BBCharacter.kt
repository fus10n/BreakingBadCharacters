package com.example.breakingbadcharacters.content.domains

import com.google.gson.annotations.SerializedName

/**
 * Class representing a Breaking Bad Character object.
 */
class BBCharacter(
    @SerializedName("char_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("occupation") val occupation: Array<String>?,
    @SerializedName("img") val imageUrl: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("appearance") val appearance: IntArray?,
    @SerializedName("portrayed") val portrayed: String?,
    @SerializedName("category") val series: String?,
    @SerializedName("better_call_saul_appearance") val bcsAppearance: IntArray?
) {

    override fun equals(other: Any?): Boolean = (other is BBCharacter && other.id == id)

    override fun hashCode(): Int = toString().hashCode()

    override fun toString(): String =
        "Character(id: $id, name: $name, birthday: $birthday, occupation: ${occupation.contentToString()}, " +
        "img: $imageUrl, status: $status, nickname: $nickname, appearance: ${appearance.contentToString()}, " +
        "portrayed: $portrayed, category: $series, better_call_saul_appearance: ${bcsAppearance.contentToString()})"

}
