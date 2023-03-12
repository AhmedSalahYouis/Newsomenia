package com.asalah.newsomenia.feature_news_listing.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.asalah.newsomenia.R
import com.asalah.newsomenia.core.presentation.app_components.news.NewsItem
import com.asalah.newsomenia.core.presentation.compose_components.paging_state.PagingLoadingState
import com.asalah.newsomenia.core.presentation.design_system.theme.NewsomeniaColors
import com.asalah.newsomenia.core.presentation.design_system.theme.NewsomeniaTypography

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val newsList =
        viewModel.getNews("us","entertainment")
            .collectAsLazyPagingItems()


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(8.dp),
            text = stringResource(id = R.string.news),
            color = NewsomeniaColors.designSystem.PrimaryText,
            style = NewsomeniaTypography.text18
        )
        LazyColumn {

            if (newsList.itemCount > 0) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(8.dp),
                        text = stringResource(id = R.string.news),
                        color = NewsomeniaColors.designSystem.Neutral30,
                        style = NewsomeniaTypography.text14
                    )
                }
                items(newsList.itemCount) {
                    NewsItem(article = newsList[it])
                }
            }

            item {
                PagingLoadingState(pagingItems = newsList)
            }
        }
    }

}