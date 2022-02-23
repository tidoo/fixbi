package com.arsi.fixbywallpapers.networking


import com.arsi.fixbywallpapers.constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//    @Singleton
//    @Provides
//    fun provideHttpClient(): OkHttpClient {
//        return OkHttpClient
//            .Builder()
//            .readTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .build()
//    }
//    @Singleton
//    @Provides
//    fun provideConverterFactory(): GsonConverterFactory =
//        GsonConverterFactory.create()
//    @Singleton
//    @Provides
//    fun provideRetrofit(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }
//    @Singleton
//    @Provides
//    fun provideCurrencyService(retrofit: Retrofit): RetrofitService =
//        retrofit.create(RetrofitService::class.java)
//}

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