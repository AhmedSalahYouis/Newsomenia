package com.asalah.newsomenia.feature_news_listing.domain.news.usecase

import com.asalah.newsomenia.core.di.qualifiers.IoDispatcher
import com.asalah.newsomenia.core.domain.common.usecase.ApiUseCase
import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import com.asalah.newsomenia.feature_news_listing.domain.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsNewsRepository: NewsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ApiUseCase<NewsUseCase.Params, NewsListResponse>(
    ioDispatcher
) {
    override suspend fun execute(parameters: Params): Response<NewsListResponse> =
        newsNewsRepository.getNews(
            parameters.country,
            parameters.category,
            parameters.page
        )

    data class Params(
        val country: String?,
        val category: String?,
        val page: Int,
    )
}
