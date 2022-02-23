package com.arsi.fixbywallpapers.networking


import com.arsi.fixbywallpapers.constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    val retrofitClient: Retrofit.Builder by lazy {

        val okhttpClient = OkHttpClient.Builder()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())


    }


    val retrofitService: RetrofitService by lazy {
        retrofitClient
            .build()
            .create(RetrofitService::class.java)
    }

}