package com.thecavga.querygit.network

import android.util.Log
import com.thecavga.querygit.BuildConfig
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient


class Network {

    fun NetworkClient() {

    }

    companion object {

        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {

            if (retrofit == null) {
                val builder = OkHttpClient.Builder()
                val okHttpClient = builder.build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

            }


            Log.w("base_url", retrofit!!.baseUrl().toString())
            return retrofit!!
        }
    }
}