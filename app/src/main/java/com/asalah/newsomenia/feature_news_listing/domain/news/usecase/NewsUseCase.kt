package com.asalah.newsomenia.feature_news_listing.domain.news.usecase

import com.asalah.newsomenia.core.di.qualifiers.IoDispatcher
import com.asalah.newsomenia.core.domain.common.usecase.ApiUseCase
import com.asalah.newsomenia.core.domain.news_list.entity.NewsDbListResponse
import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticleDb
import com.asalah.newsomenia.feature_news_listing.data.repository.INewsRepository
import com.asalah.newsomenia.feature_news_listing.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsNewsRepository: INewsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ApiUseCase<NewsUseCase.Params, NewsDbListResponse>(
    ioDispatcher
) {
    override suspend fun execute(parameters: Params): Flow<Response<NewsDbListResponse>> =
        newsNewsRepository.getNewsArticles(
            parameters.page
        )

    data class Params(
        val page: Int,
    )
}
