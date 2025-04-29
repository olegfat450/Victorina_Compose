package com.example.victorina_compose.data

import com.example.victorina_compose.data.RetrofitInstance.retrofit

class Repository {
    suspend fun getQuestion(): Question {
        return retrofit.getQuestion()
    }

}