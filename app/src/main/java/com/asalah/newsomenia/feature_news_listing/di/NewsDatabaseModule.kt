package com.asalah.newsomenia.feature_news_listing.di

import android.app.Application
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticlesDao
import com.asalah.newsomenia.feature_news_listing.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): NewsDatabase = NewsDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideUserDao(db: NewsDatabase): NewsArticlesDao = db.newsArticlesDao()
}