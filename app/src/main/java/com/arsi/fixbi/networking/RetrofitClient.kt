package com.arsi.fixbi.networking


import com.arsi.fixbi.constants.BASE_URL
import com.arsi.fixbi.utils.PrettyPrintHTTPLogs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


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

        val httpLoggingInterceptor = HttpLoggingInterceptor(PrettyPrintHTTPLogs())
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okhttpClient.addInterceptor(httpLoggingInterceptor)
        okhttpClient.readTimeout(30, TimeUnit.SECONDS)
        okhttpClient.connectTimeout(30, TimeUnit.SECONDS)

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