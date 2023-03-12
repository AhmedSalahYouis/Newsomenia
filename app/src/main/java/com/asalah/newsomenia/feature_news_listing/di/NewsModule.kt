package com.asalah.newsomenia.feature_news_listing.di

import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.asalah.newsomenia.feature_news_listing.data.remote.NewsApi
import com.asalah.newsomenia.feature_news_listing.data.repository.DefaultNewsRepository
import com.asalah.newsomenia.feature_news_listing.data.repository.NewsRepositoryImpl
import com.asalah.newsomenia.feature_news_listing.domain.NewsRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun providesNewsRepository(newsApi: NewsApi, newsArticlesDao: NewsArticlesDao): DefaultNewsRepository =
        DefaultNewsRepository(newsArticlesDao, newsApi)


    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)
}