package com.example.animewifuapplication.api

import com.example.animewifuapplication.models.WifuPicsResponseModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface AnimeApi {


    @POST("{dynamicEndPoint}")
    suspend fun  getAllPics(
        @Path ("dynamicEndPoint") dynamicEndPoint:String
    ):Response<WifuPicsResponseModel>

}