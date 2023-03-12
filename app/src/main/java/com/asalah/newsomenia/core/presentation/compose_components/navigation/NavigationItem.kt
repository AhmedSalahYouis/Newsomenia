package com.asalah.newsomenia.core.presentation.compose_components.navigation

import com.asalah.newsomenia.R

sealed class NavigationItem(var screen: Screen, var title: Int) {
    object News : NavigationItem(
        Screen.News,
        R.string.news
    )
}









