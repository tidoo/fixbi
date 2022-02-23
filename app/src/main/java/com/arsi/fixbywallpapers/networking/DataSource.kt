package com.arsi.fixbywallpapers.networking

import javax.inject.Inject

class DataSource @Inject constructor(private val retrofitService: RetrofitService) {


    suspend fun getBatmanImages() = retrofitService.getBatmanImages()


}