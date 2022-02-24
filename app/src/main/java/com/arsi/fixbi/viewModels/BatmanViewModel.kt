package com.arsi.fixbi.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arsi.fixbi.FixbiApplication
import com.arsi.fixbi.model.BatmanApiResponse
import com.arsi.fixbi.model.BatmanModel
import com.arsi.fixbi.networking.NetworkResult
import com.arsi.fixbi.networking.Repository
import com.arsi.fixbi.utils.SingleLiveEvent
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

    val imageClickEvent = SingleLiveEvent<BatmanModel>()


    fun onClickImage(batmanModel: BatmanModel, position: Int) {
        imageClickEvent.postValue(batmanModel)
    }

}