package com.example.popularnews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.popularnews.data.api.NewsApiService
import com.example.popularnews.data.entity.Article
import com.example.popularnews.di.NewsPageSource
import com.example.popularnews.utils.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val api: NewsApiService) {
    fun getNewsResult(query: String): Flow<PagingData<Article>> {
        return Pager(PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE
        ), pagingSourceFactory = { NewsPageSource(api, query) }).flow
    }
}