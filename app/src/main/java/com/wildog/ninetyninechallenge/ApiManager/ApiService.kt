package com.wildog.ninetyninechallenge.ApiManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    val apiClient: RetrofitApi

    init {
        val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()

        val apiClientConfig =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Routes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiClient = apiClientConfig.create(RetrofitApi::class.java)
    }
}

