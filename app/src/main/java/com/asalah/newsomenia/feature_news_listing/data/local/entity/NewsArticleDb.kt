package com.asalah.newsomenia.feature_news_listing.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asalah.newsomenia.feature_news_listing.data.local.NewsArticleDb.NewsArticles.tableName

/**
 * Describes how the news article are stored.
 */
@Entity(tableName = tableName)
data class NewsArticleDb(

    /**
     * Primary key for Room.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = NewsArticles.Column.title)
    val title: String? = null,

    @ColumnInfo(name = NewsArticles.Column.urlToImage)
    val urlToImage: String? = null,

    ) {

    object NewsArticles {
        const val tableName = "news_article"

        object Column {
            const val id = "id"
            const val title = "title"
            const val urlToImage = "urlToImage"
        }
    }
}