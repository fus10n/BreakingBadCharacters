package com.example.breakingbadcharacters.content

import android.util.Log

import com.example.breakingbadcharacters.Utils
import com.example.breakingbadcharacters.content.domains.BBCharacter
import com.example.breakingbadcharacters.content.domains.CharactersJsonArray
import com.example.breakingbadcharacters.network.ApiRequestExecutor

import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException

import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Error

object ContentManager : ApiRequestExecutor.Callback {

    interface FetchCallback {
        fun onDataFetched(characters: MutableList<BBCharacter>)
    }

    private val TAG = Utils.getTag(this::class)
    private val gson = GsonBuilder().create()

    private var callback: FetchCallback? = null

    fun fetchData(callback: FetchCallback) {
        if (this.callback == null) {
            this.callback = callback
            ApiRequestExecutor.sendGetRequest(this)
        }
    }

    override fun onRequestResult(isSuccess: Boolean, responseStream: InputStream?) {
        if (!isSuccess) {
            Log.e(TAG, "Request failed")
            try {
                Log.e(TAG, InputStreamReader(responseStream).readText())
            } catch (e: Error) {
                e.printStackTrace()
            }
            return
        }

        Log.d(TAG, "Request successful")

        callback?.let {
            val json = parseResponse(responseStream)
            if (json != null) {
                Log.d(TAG, "Characters fetched: ${json.charactersArray.size}")
                it.onDataFetched(mutableListOf(*json.charactersArray))
            } else {
                Log.e(TAG, "null JsonResponse")
            }
        }
    }

    fun parseResponse(inputStream: InputStream?): CharactersJsonArray? {
        val responseTextBuilder = StringBuilder("{\"")
            .append(CharactersJsonArray.MAIN_ARRAY_PARAM)
            .append("\":")

        try {
            responseTextBuilder
                .append(InputStreamReader(inputStream).readText())
                .append('}')
        } catch (e: Error) {
            e.printStackTrace()
            return null
        }

        try {
            return gson.fromJson(responseTextBuilder.toString(), CharactersJsonArray::class.java)
        } catch (e: JsonParseException) {
            e.printStackTrace()
        }

        return null
    }

}