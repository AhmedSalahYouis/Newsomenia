package com.asalah.newsomenia.core.domain.news_items.entity

import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticleDb
import com.squareup.moshi.Json

data class Article(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null
){
    fun toStorage(): NewsArticleDb {
        return NewsArticleDb(
            title = title,
            urlToImage = urlToImage,
        )
    }

}
