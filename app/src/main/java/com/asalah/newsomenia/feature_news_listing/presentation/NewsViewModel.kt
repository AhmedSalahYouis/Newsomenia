package com.asalah.newsomenia.feature_news_listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.asalah.newsomenia.core.domain.news_items.entity.Article
import com.asalah.newsomenia.core.util.constants.ApiConstants.Companion.PAGINATION_PAGE_SIZE
import com.asalah.newsomenia.feature_news_listing.data.remote.source.NewsSource
import com.asalah.newsomenia.feature_news_listing.domain.news.usecase.NewsUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    fun getNews(
        country: String?,
        category: String?,
    ): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = PAGINATION_PAGE_SIZE)) {
            NewsSource(
                newsUseCase,
                country = country,
                category = category
            )
        }.flow.cachedIn(viewModelScope)
}