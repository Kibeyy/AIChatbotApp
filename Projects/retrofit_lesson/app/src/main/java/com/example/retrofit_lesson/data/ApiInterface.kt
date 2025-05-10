package com.example.retrofit_lesson.data

import com.example.retrofit_lesson.models.CatFacts
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/fact")
    suspend fun getFact():Response<CatFacts>
}