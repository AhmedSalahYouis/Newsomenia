package com.asalah.newsomenia.feature_news_listing.domain

import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(
        country: String?,
        category: String?,
        page: Int,
    ): Response<NewsListResponse>
}