package com.arsi.fixbi.networking

import com.arsi.fixbi.constants.BATMAN_API
import com.arsi.fixbi.model.BatmanApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET(BATMAN_API)
    suspend fun getBatmanImages(): Response<BatmanApiResponse>

}