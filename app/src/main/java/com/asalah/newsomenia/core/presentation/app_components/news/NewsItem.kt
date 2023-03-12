package com.asalah.newsomenia.core.presentation.app_components.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.asalah.newsomenia.core.domain.news_items.entity.Article
import com.asalah.newsomenia.core.presentation.compose_components.image.AsyncImage
import com.asalah.newsomenia.core.presentation.design_system.theme.NewsomeniaColors
import com.asalah.newsomenia.core.presentation.design_system.theme.NewsomeniaTypography
import com.asalah.newsomenia.core.util.time.toLocalTime


const val ARTICLE_PLACE_HOLDER = "https://www.kasandbox.org/programming-images/avatars/cs-hopper-cool.png"//placeHolder

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    article: Article?
) {
    ConstraintLayout(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        val (imgCover, txtTitle) = createRefs()

        Box(
            modifier = modifier
                .padding(vertical = 4.dp)
                .constrainAs(imgCover) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .size(96.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = article?.urlToImage ?: ARTICLE_PLACE_HOLDER,
                requestBuilder = { crossfade(true) },
                contentDescription = "article image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(txtTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(imgCover.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(5.dp)
                .wrapContentHeight()
                .padding(8.dp),
            text = article?.title ?: "",
            color = NewsomeniaColors.designSystem.Neutral30,
            style = NewsomeniaTypography.text14Bold
        )
    }


}