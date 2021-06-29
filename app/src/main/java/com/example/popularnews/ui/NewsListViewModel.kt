package com.example.popularnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popularnews.data.entity.Article
import com.example.popularnews.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsListViewModel(private val repository: NewsRepository) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentNewsResult: Flow<PagingData<Article>>? = null

    fun getNews(query: String): Flow<PagingData<Article>> {
        val lastResult = currentNewsResult
        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = query
        val newResult: Flow<PagingData<Article>> = repository.getNewsResult(query)
            .cachedIn(viewModelScope)
        currentNewsResult = newResult
        return newResult
    }
}