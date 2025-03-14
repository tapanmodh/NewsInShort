package com.tm.newsinshort.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tm.newsinshort.ui.components.EmptyStateComponent
import com.tm.newsinshort.ui.components.Loader
import com.tm.newsinshort.ui.components.NewsRowComponent
import com.tm.newsinshort.ui.viewmodel.NewsViewModel
import com.tm.utilities.ResourceState

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()) {

    val newsResponse = newsViewModel.news.collectAsState().value

    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        100
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp,
    ) { page: Int ->

        when (newsResponse) {

            is ResourceState.Loading<*> -> {
                Log.d(TAG, "Inside_Loading")
                Loader()
            }

            is ResourceState.Success<*> -> {
                val response = (newsResponse as ResourceState.Success).data
                Log.d(TAG, "Inside_Success ${response.status} = ${response.totalResults}")
                if (response.articles.isNotEmpty()) {
                    NewsRowComponent(page, response.articles[page])
                } else {
                    EmptyStateComponent()
                }
            }

            is ResourceState.Error<*> -> {
                val error = newsResponse as ResourceState.Error
                Log.d(TAG, "Inside_Error $error")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {

    }
}