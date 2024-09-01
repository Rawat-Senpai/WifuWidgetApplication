package com.example.animewifuapplication.repository

import android.util.Log
import com.example.animewifuapplication.api.AnimeApi
import com.example.animewifuapplication.models.WifuPicsResponseModel
import com.example.animewifuapplication.utils.NetworkResult
import com.example.animewifuapplication.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeApi: AnimeApi) {

    val emptyJsonBody = RequestBody.create("application/json".toMediaTypeOrNull(), "{}")

    private val _waifuPicResponse =
        MutableStateFlow<NetworkResult<WifuPicsResponseModel>?>(NetworkResult.Loading())

    val waifuPicResponse: StateFlow<NetworkResult<WifuPicsResponseModel>?> get() = _waifuPicResponse


    suspend fun getWaifuPics(type: String, category: String) {

        _waifuPicResponse.value = NetworkResult.Loading()

        val url = "$type/$category"
        Log.d("checkingFinalUrl",url)
        val result = safeApiCall { animeApi.getAllPics(type,category,emptyJsonBody) }
        Log.d("checkingFinalApi",result.toString())
        _waifuPicResponse.value = result


    }

    fun extractImageUrls(response: WifuPicsResponseModel?): List<String> {

        val animeCharacterImages = ArrayList<String>()
        for (data in response?.files!!) {
            animeCharacterImages.add(data)
        }

        return animeCharacterImages
    }


}