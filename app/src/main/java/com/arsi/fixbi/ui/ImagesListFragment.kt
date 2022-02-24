package com.arsi.fixbi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arsi.fixbi.R
import com.arsi.fixbi.base.BaseFragment
import com.arsi.fixbi.databinding.FragmentImagesListBinding
import com.arsi.fixbi.model.BatmanModel
import com.arsi.fixbi.networking.DataSource
import com.arsi.fixbi.networking.NetworkResult
import com.arsi.fixbi.networking.Repository
import com.arsi.fixbi.networking.RetrofitClient
import com.arsi.fixbi.utils.activityViewModelBuilder
import com.arsi.fixbi.utils.setRecyclerViewAdapter
import com.arsi.fixbi.utils.toast
import com.arsi.fixbi.viewModels.BatmanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesListFragment : BaseFragment() {


    //    private val batmanViewModel: BatmanViewModel by viewModels(factoryProducer = )
//    private val batmanViewModel by viewModels<BatmanViewModel>()
    private val batmanViewModel by activityViewModelBuilder { BatmanViewModel(Repository(DataSource(RetrofitClient.retrofitService))) }


    private lateinit var binding: FragmentImagesListBinding

    private var images: ArrayList<BatmanModel> = ArrayList()

    private var alertDialog: AlertDialog? = null

    lateinit var layoutManager : StaggeredGridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImagesListBinding.inflate(layoutInflater)


        init()

        return binding.root
    }

    private fun init() {
        setRecyclerView()
        setCallBacks()
        observeData()
        fetchData()
    }

    private fun fetchData() {
        batmanViewModel.getBatmanImages()
    }

    private fun setRecyclerView() {
        layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        binding.rvImages.layoutManager = layoutManager
        binding.rvImages.setRecyclerViewAdapter(batmanViewModel, R.layout.list_item_images, images)
    }

    private fun setCallBacks() {
        batmanViewModel.imageClickEvent.observe(viewLifecycleOwner) { batman ->
            alertDialog = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("See Full Image") { dialog, _ ->
                        dialog.dismiss()
                        findNavController().navigate(ImagesListFragmentDirections.actionImagesListFragmentToImageDetailsFragment(batman))
                    }
                    setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                }


                // Create the AlertDialog
                builder.create()
            }
            alertDialog!!.show()
        }
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                batmanViewModel.getBatmanImages()
            }
            btnSize1.setOnClickListener {
                layoutManager.spanCount = 1
                rvImages.layoutManager = layoutManager
            }
            btnSize2.setOnClickListener {
                layoutManager.spanCount = 2
                rvImages.layoutManager = layoutManager
            }
            btnSize3.setOnClickListener {
                layoutManager.spanCount = 3
                rvImages.layoutManager = layoutManager
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        batmanViewModel.response.observe(viewLifecycleOwner) { response ->
            binding.swipeRefresh.isRefreshing = false
            when (response) {
                is NetworkResult.Success -> {
                    hideProgressDialog()
                    response.data!!.batmanImages.shuffle()
                    Log.wtf("CALL", "Success")
                    images.clear()
                    images.addAll(response.data.batmanImages)
                    binding.rvImages.adapter!!.notifyDataSetChanged()
                }
                is NetworkResult.Error -> {

                    hideProgressDialog()
                    toast("Error")
                    Log.wtf("CALL", "Error")
                }
                is NetworkResult.Loading -> {
                    showProgressDialog()

                    Log.wtf("CALL", "Loading")
                }
            }
        }
    }

}