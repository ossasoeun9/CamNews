package com.example.camnews.models.data

import com.example.camnews.utils.Utils
import java.io.Serializable

data class Article(
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    val author: String?
): Serializable {
    val subtitle get() = "${author ?: "Unknown"} - ${Utils.stringToDateFormatted(publishedAt)}"
}