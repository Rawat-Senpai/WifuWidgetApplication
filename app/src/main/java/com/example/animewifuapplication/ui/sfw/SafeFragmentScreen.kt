package com.example.animewifuapplication.ui.sfw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.animewifuapplication.R
import com.example.animewifuapplication.adapter.StaggeredGridAdapter
import com.example.animewifuapplication.adapter.TypePickerAdapter
import com.example.animewifuapplication.databinding.FragmentSafeScreenBinding
import com.example.animewifuapplication.utils.NetworkResult
import com.example.animewifuapplication.utils.sfwTypeList
import com.example.animewifuapplication.viewModel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint

class SafeFragmentScreen : Fragment() {

    private var _binding:FragmentSafeScreenBinding?= null
    private val binding get() = _binding!!


    private val animeViewModel by viewModels<AnimeViewModel>()
    private  val adapter = StaggeredGridAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_safe_screen, container, false)

        _binding = FragmentSafeScreenBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        bindViews()
        animeViewModel.getAnimeImages("sfw","neko")

    }

    private fun bindViews() {

        binding.apply {


        animeImages.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        animeImages.adapter = adapter
        }


    }

    private fun bindObservers() {
            viewLifecycleOwner.lifecycleScope.launch{

                launch {
                    animeViewModel.getAnimeImages.collect{

                        when(it){
                            is NetworkResult.Error -> {}
                            is NetworkResult.Loading -> {}
                            is NetworkResult.Success -> {

                                adapter.submitList(it.data!!.files)

                            }
                            null -> {}
                        }

                    }
                }

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}