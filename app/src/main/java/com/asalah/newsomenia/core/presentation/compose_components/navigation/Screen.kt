package com.asalah.newsomenia.core.presentation.compose_components.navigation

sealed class Screen(val route: String) {
    object News : Screen("news_listing_screen")
}