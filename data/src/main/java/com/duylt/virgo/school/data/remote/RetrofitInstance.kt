package com.duylt.virgo.school.data.remote

import com.duylt.virgo.school.data.remote.api.WebApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {

        private fun getRetrofitBuilder() =
            Retrofit.Builder()
                .baseUrl("http://192.168.1.102:7903/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

        private val gson: Gson = GsonBuilder().setDateFormat("dd/MM/yyyy").create()

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val webService: WebApiService = getRetrofitBuilder().create(WebApiService::class.java)
    }
}