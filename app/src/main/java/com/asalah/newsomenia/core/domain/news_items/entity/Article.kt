package com.asalah.newsomenia.core.domain.news_items.entity

import com.squareup.moshi.Json

data class Article(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null
)
