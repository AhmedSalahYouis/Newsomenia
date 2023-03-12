package com.asalah.newsomenia.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.asalah.newsomenia.core.presentation.design_system.theme.NewsomeniaTheme
import com.asalah.newsomenia.core.presentation.main_screen.MainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsomeniaTheme(false) {
                MainScreen()
            }
        }
    }
}
