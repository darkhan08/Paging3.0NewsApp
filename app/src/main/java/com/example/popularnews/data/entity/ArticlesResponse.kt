package com.example.popularnews.data.entity

import androidx.annotation.IntRange
import kotlinx.serialization.SerialName


data class ArticlesResponse(
    @SerialName("status") val status: String,
    @SerialName("totalResults") @IntRange(from = 1) val totalResults: Int,
    @SerialName("message") val message: String? = null,
    @SerialName("articles") val articles: List<Article>,
)