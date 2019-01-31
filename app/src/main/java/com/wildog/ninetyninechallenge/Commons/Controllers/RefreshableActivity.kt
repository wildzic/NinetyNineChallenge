package com.wildog.ninetyninechallenge.Commons.Controllers

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.wildog.ninetyninechallenge.Commons.Components.KtCallback
import retrofit2.Call

abstract class RefreshableActivity : AppCompatActivity() {

    private val REFRESH_TIME = 5000L

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initHandler()
    }

    override fun onStart() {
        super.onStart()
        mHandler.post(mRunnable)
    }

    override fun onStop() {
        super.onStop()
        mHandler.removeCallbacks(mRunnable)
    }

    private fun initHandler() {
        mHandler = Handler()
        mRunnable = Runnable {
            refreshActivity()
            mHandler.postDelayed(
                mRunnable,
                REFRESH_TIME
            )
        }
    }

    fun <T> Call<T>.enqueue(callback: KtCallback<T>.() -> Unit) {
        val callBackKt = KtCallback<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }

    abstract fun refreshActivity()

}