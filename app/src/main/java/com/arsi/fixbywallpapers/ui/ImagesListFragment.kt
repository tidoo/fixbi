package com.arsi.fixbywallpapers.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.arsi.fixbywallpapers.R
import com.arsi.fixbywallpapers.databinding.FragmentImagesListBinding
import com.arsi.fixbywallpapers.networking.*
import com.arsi.fixbywallpapers.utils.activityViewModelBuilder
import com.arsi.fixbywallpapers.utils.toast
import com.arsi.fixbywallpapers.viewModels.BatmanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesListFragment : Fragment() {


//    private val batmanViewModel: BatmanViewModel by viewModels(factoryProducer = )
//    private val batmanViewModel by viewModels<BatmanViewModel>()
    private val batmanViewModel by activityViewModelBuilder { BatmanViewModel(repository = Repository(DataSource(RetrofitClient.retrofitService))) }


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
                    toast("Success")
                    Log.wtf("CALL","Success")
                }
                is NetworkResult.Error -> {
                    toast("Error")
                    Log.wtf("CALL","Error")
                }
                is NetworkResult.Loading -> {
                    toast("Loading")
                    Log.wtf("CALL","Loading")
                }
            }
        }
    }

}