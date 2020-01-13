package com.app.gitrepositories.utils

import com.app.gitrepositories.service.APIService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mayank on October 12 2019
 */
object NetworkUtils {

    fun buildApiService(): APIService {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(StringContract.BASE_URL)
            .build()

        return retrofit.create(APIService::class.java)
    }

}