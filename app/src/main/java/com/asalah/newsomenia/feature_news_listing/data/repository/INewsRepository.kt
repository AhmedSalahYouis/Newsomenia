package com.asalah.newsomenia.feature_news_listing.data.repository

import android.content.Context
import com.asalah.newsomenia.core.domain.news_items.entity.Article
import com.asalah.newsomenia.core.domain.news_list.entity.NewsDbListResponse
import com.asalah.newsomenia.core.domain.news_list.entity.NewsListResponse
import com.asalah.newsomenia.core.util.constants.ApiConstants
import com.asalah.newsomenia.core.util.network.NetworkHelper
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticleDb
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticlesDao
import com.asalah.newsomenia.feature_news_listing.data.remote.NewsApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface INewsRepository {

    /**
     * Gets tne cached news article from database and tries to get
     * fresh news articles from web and save into database
     * if that fails then continues showing cached data.
     */
    suspend fun getNewsArticles(page: Int): Response<NewsDbListResponse>

    /**
     * Gets fresh news from web.
     */
    suspend fun getNewsFromWebservice(
        page: Int
    ): Response<NewsListResponse>
}

@Singleton
class DefaultNewsRepository @Inject constructor(
    private val context:Context,
    private val newsDao: NewsArticlesDao,
    private val newsApi: NewsApi
) : INewsRepository {

    override suspend fun getNewsArticles(page: Int): Response<NewsDbListResponse> {

        //Try to fetch fresh news from web + cache if any
        if (NetworkHelper.isNetworkConnected(context)){
            val freshNews = getNewsFromWebservice(page)
            freshNews.body()?.articles?.map { it.toStorage() }?.let(newsDao::cacheArticles)

        }
        // Get news from cache [cache is always source of truth]
        val cachedNews = newsDao.getNewsArticles()

        return Response.success(NewsDbListResponse(cachedNews))
    }

    override suspend fun getNewsFromWebservice(
        page: Int
    ): Response<NewsListResponse> =
        withContext(Dispatchers.IO) {
            newsApi.getNews(
                ApiConstants.COUNTRY,
                ApiConstants.CATEGORY,
                page = page
            )
        }
}

@Module
@InstallIn(SingletonComponent::class)
interface NewsRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultNewsRepository): INewsRepository
}