package com.example.animewifuapplication.utils

import android.util.Log
import retrofit2.Response


suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = apiCall()

        Log.d("checkingResponse",response.toString())
        if (response.isSuccessful && response.body() != null) {

            Log.d("checkingResponse",response.body().toString())
            NetworkResult.Success(response.body()!!)

        } else if  (response.isSuccessful && response.body() == null) {
            Log.d("checkingResponseNull",response.toString())
            NetworkResult.Error("Response body is null")
        }else{
            Log.d("checkingResponseError",response.message().toString())
            NetworkResult.Error(response.message())
        }


    } catch (e: Exception) {
        NetworkResult.Error(e.message ?: "An unknown error occurred")
    }


}


fun getRandomCategory(): String {
    val categories = listOf(
        "waifu", "neko", "shinobu", "megumin", "bully", "cuddle", "cry", "hug",
        "awoo", "kiss", "lick", "pat", "smug", "bonk", "yeet", "blush", "smile",
        "wave", "highfive", "handhold", "nom", "bite", "glomp", "slap", "kill",
        "kick", "happy", "wink", "poke", "dance", "cringe"
    )
    return categories.random() // Returns a random category from the list
}
