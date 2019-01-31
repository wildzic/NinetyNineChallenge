package com.wildog.ninetyninechallenge.Commons.Components

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KtCallback<T> : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }
}