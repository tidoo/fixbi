package com.arsi.fixbywallpapers.networking

import javax.inject.Inject

class DataSource @Inject constructor(private val dogService: RetrofitService) {


    suspend fun getBatmanImages() = dogService.getBatmanImages()


}