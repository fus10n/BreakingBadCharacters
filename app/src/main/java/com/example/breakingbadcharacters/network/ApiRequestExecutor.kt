package com.example.breakingbadcharacters.network

import android.os.Handler
import android.os.Looper
import android.util.Log

import com.example.breakingbadcharacters.Utils

import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object ApiRequestExecutor {

    interface Callback {
        fun onRequestResult(isSuccess: Boolean, responseStream: InputStream?)
    }

    private const val API_URL = "https://breakingbadapi.com/api/characters"

    private val TAG = Utils.getTag(this::class)
    private val mainHandler = Handler(Looper.getMainLooper())

    fun sendGetRequest(callback: Callback) {
        Thread(Runnable {
            val urlConnection = URL(API_URL).openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")

            try {
                urlConnection.connect()
                val responseCode = urlConnection.responseCode

                Log.d(TAG, "response: $responseCode - ${urlConnection.responseMessage}")

                if (responseCode in 200..399) {
                    mainHandler.post { callback.onRequestResult(true, urlConnection.inputStream) }
                    return@Runnable
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }

            mainHandler.post { callback.onRequestResult(false, urlConnection.errorStream) }
        }).start()
    }

}