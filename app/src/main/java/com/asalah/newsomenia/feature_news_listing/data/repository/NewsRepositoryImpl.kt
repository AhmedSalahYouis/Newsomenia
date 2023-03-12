package com.asalah.newsomenia.feature_news_listing.data.repository

import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticlesDao
import com.asalah.newsomenia.feature_news_listing.data.remote.NewsApi
import com.asalah.newsomenia.feature_news_listing.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsArticlesDao,
    private val newsApi: NewsApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsRepository {

    override suspend fun getNews(
        page: Int
    ): Response<NewsListResponse> =
        withContext(ioDispatcher) {

            newsApi.getNews(
                page = page
            )
        }
}