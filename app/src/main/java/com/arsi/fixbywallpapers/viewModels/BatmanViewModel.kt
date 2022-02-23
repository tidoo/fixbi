package com.arsi.fixbywallpapers.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arsi.fixbywallpapers.FixbiApplication
import com.arsi.fixbywallpapers.model.BatmanApiResponse
import com.arsi.fixbywallpapers.networking.NetworkResult
import com.arsi.fixbywallpapers.networking.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BatmanViewModel @Inject constructor(private val repository: Repository, application: Application = FixbiApplication()) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<BatmanApiResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<BatmanApiResponse>> = _response


    fun getBatmanImages() = viewModelScope.launch {
        repository.getBatmanImages().collect { values ->
            _response.value = values
        }
    }

}