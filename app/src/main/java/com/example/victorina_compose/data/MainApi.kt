package com.example.victorina_compose.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("millionaire.php")
    suspend fun getQuestion(
        @Query("qType") qType: Int = 2,
        @Query("count") count: Int = 1
    ): Question
}