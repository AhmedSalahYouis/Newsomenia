package com.asalah.newsomenia.feature_news_listing.data.remote.source

import com.asalah.newsomenia.core.domain.news_items.entity.Article
import com.asalah.newsomenia.core.util.network.ApiResult
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticleDb
import com.asalah.newsomenia.feature_news_listing.domain.news.usecase.NewsUseCase

class NewsSource(
    private val newsUseCase: NewsUseCase,
) : PagingSource<Int, NewsArticleDb>() {

    override fun getRefreshKey(state: PagingState<Int, NewsArticleDb>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticleDb> {
        return try {
            val page = params.key ?: 1
            val response = newsUseCase(
                NewsUseCase.Params(
                    page = page
                )
            )

            when (response) {
                is ApiResult.ServerError -> {
                    LoadResult.Error(Exception(response.errorBody.message))
                }
                is ApiResult.Error -> {
                    LoadResult.Error(Exception(response.exception.message))
                }
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = response.data.articles,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (response.data.articles.isEmpty()) null else page.plus(1),
                    )
                }
                else -> {
                    LoadResult.Error(Exception("Loading"))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}