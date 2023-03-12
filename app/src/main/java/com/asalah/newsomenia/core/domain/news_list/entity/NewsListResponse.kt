package com.asalah.newsomenia.core.domain.news_list.entity

import com.squareup.moshi.Json
import com.asalah.newsomenia.core.domain.news_items.entity.Article

data class NewsListResponse(
    @Json(name = "articles")
    val articles: List<Article>
)