package com.example.weather.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseURL = "https://api.weatherapi.com"
    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherAPI: WeatherAPI = getInstance().create(WeatherAPI::class.java)

}