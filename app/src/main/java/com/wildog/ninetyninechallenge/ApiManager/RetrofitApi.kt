package com.wildog.ninetyninechallenge.ApiManager

import com.wildog.ninetyninechallenge.Model.Company
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    @GET(Routes.GET_COMPANIES)
    fun getCountries(): Call<ArrayList<Company>>

    @GET(Routes.GET_COMPANY + "/{id}")
    fun getCompanyById(@Path("id") id: Int): Call<Company>
}