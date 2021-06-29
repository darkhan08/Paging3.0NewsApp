package com.example.popularnews.data.api

import androidx.annotation.IntRange
import com.example.popularnews.data.entity.ArticlesResponse
import com.example.popularnews.utils.DEFAULT_PAGE_SIZE
import com.example.popularnews.utils.MAX_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String? = null,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE
    ): Response<ArticlesResponse>
}