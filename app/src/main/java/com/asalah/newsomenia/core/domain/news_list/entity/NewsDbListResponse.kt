package com.asalah.newsomenia.core.domain.news_list.entity

import com.asalah.newsomenia.core.domain.news_items.entity.Article
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticleDb

data class NewsDbListResponse(
    val articles: List<NewsArticleDb>
)
