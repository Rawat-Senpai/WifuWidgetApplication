package com.example.animewifuapplication.api

import com.example.animewifuapplication.models.WifuPicsResponseModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface AnimeApi {
    @POST("/many/{type}/{category}")
    @Headers("Content-Type: application/json")
    suspend fun  getAllPics(
        @Path ("type") type:String,
        @Path("category") category:String,
        @Body body: RequestBody
    ):Response<WifuPicsResponseModel>

}