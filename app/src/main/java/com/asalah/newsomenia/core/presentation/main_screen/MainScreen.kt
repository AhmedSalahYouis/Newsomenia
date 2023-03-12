package com.asalah.newsomenia.core.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.asalah.newsomenia.core.presentation.compose_components.navigation.Navigation
import com.asalah.newsomenia.core.presentation.design_system.theme.NewsomeniaColors


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = NewsomeniaColors.designSystem.PrimaryBackground
    )
}