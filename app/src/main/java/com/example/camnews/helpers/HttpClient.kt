package com.example.camnews.helpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient {
    companion object {
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.HOST_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}