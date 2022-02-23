package com.arsi.fixbywallpapers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.arsi.fixbywallpapers.R
import com.arsi.fixbywallpapers.databinding.FragmentImagesListBinding
import com.arsi.fixbywallpapers.networking.NetworkResult
import com.arsi.fixbywallpapers.viewModels.BatmanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesListFragment : Fragment() {


    private val batmanViewModel: BatmanViewModel by navGraphViewModels(R.id.nav_images_flow)
    lateinit var binding : FragmentImagesListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentImagesListBinding.inflate(layoutInflater)


        init()

        return binding.root
    }

    fun init(){
        fetchData()
    }

    private fun fetchData() {
        batmanViewModel.getBatmanImages()
        batmanViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                }
            }
        }
    }

}