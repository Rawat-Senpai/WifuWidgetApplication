package com.example.animewifuapplication.repository

import com.example.animewifuapplication.api.AnimeApi
import com.example.animewifuapplication.models.WifuPicsResponseModel
import com.example.animewifuapplication.utils.NetworkResult
import com.example.animewifuapplication.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeApi: AnimeApi) {


    private val _waifuPicResponse =
        MutableStateFlow<NetworkResult<WifuPicsResponseModel>?>(NetworkResult.Loading())

    val waifuPicResponse: StateFlow<NetworkResult<WifuPicsResponseModel>?> get() = _waifuPicResponse


    suspend fun getWaifuPics(type: String, category: String) {

        _waifuPicResponse.value = NetworkResult.Loading()

        val result = safeApiCall { animeApi.getAllPics("$type/$category") }

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