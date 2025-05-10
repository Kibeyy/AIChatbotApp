package com.example.retrofit_lesson.utils

import com.example.retrofit_lesson.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    val api:ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.Base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }

}