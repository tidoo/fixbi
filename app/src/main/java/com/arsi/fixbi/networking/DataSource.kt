package com.arsi.fixbi.networking

import javax.inject.Inject

class DataSource @Inject constructor(private val retrofitService: RetrofitService) {


    suspend fun getBatmanImages() = retrofitService.getBatmanImages()


}