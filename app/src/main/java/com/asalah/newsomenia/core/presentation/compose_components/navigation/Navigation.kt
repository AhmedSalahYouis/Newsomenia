package com.asalah.newsomenia.core.presentation.compose_components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asalah.newsomenia.feature_news_listing.presentation.NewsScreen


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.News.screen.route) {
        composable(NavigationItem.News.screen.route) {
            NewsScreen()
        }
    }
}