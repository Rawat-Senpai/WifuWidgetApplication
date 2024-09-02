package com.example.animewifuapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animewifuapplication.models.WifuPicsResponseModel
import com.example.animewifuapplication.repository.AnimeRepository
import com.example.animewifuapplication.utils.NetworkResult
import com.example.animewifuapplication.widgetPackage.WaifuBannerWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val animeRepo:AnimeRepository) :ViewModel() {

    fun getAnimeImages(type:String,category:String){
            viewModelScope.launch {
                animeRepo.getWaifuPics(type,category)
            }
    }


    val getAnimeImages:StateFlow<NetworkResult<WifuPicsResponseModel>?> get() = animeRepo.waifuPicResponse


}