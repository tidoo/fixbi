package com.arsi.fixbywallpapers.networking

import com.arsi.fixbywallpapers.constants.BATMAN_API
import com.arsi.fixbywallpapers.model.BatmanApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET(BATMAN_API)
    suspend fun getBatmanImages(): Response<BatmanApiResponse>

}