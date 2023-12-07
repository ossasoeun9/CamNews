package com.example.camnews.models.data

data class ArticlesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)