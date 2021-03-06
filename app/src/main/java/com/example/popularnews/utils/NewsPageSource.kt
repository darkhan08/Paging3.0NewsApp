package com.example.popularnews.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popularnews.data.api.NewsApiService
import com.example.popularnews.data.entity.Article
import retrofit2.HttpException

class NewsPageSource(
    private val api: NewsApiService, private val query: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        val response = api.getNews(query, page, pageSize)
        return if (response.isSuccessful) {
            val articles = checkNotNull(response.body()).articles
            val nextKey = if (articles.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(articles, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }
}