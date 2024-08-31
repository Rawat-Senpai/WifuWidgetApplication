package com.example.animewifuapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.animewifuapplication.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val animeRepo:AnimeRepository) :ViewModel() {


}