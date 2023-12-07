package com.example.camnews.apis

import com.example.camnews.helpers.Constants
import com.example.camnews.models.data.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApis {
    @GET("v2/everything")
    fun getEverything(
        @Query("q") q: String = "something",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = Constants.API_KEY,
    ): Call<ArticlesResponse>

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("category") category: String,
        @Query("country") country: String? = null,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = Constants.API_KEY,
    ): Call<ArticlesResponse>
}