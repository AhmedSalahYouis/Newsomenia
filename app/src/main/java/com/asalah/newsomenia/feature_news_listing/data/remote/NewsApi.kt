package com.asalah.newsomenia.feature_news_listing.data.remote

import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import com.asalah.newsomenia.core.util.constants.ApiConstants.Companion.CATEGORY
import com.asalah.newsomenia.core.util.constants.ApiConstants.Companion.COUNTRY
import com.asalah.newsomenia.core.util.constants.ApiConstants.Companion.PAGINATION_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.*

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String? = COUNTRY,
        @Query("category") category: String? = CATEGORY,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = PAGINATION_PAGE_SIZE
    ): Response<NewsListResponse>

}

