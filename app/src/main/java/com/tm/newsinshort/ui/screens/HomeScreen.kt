package com.tm.newsinshort.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tm.newsinshort.ui.components.Loader
import com.tm.newsinshort.ui.components.NewsList
import com.tm.newsinshort.ui.viewmodel.NewsViewModel
import com.tm.utilities.ResourceState

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()) {

    val newsResponse = newsViewModel.news.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize()) {
        when (newsResponse) {

            is ResourceState.Loading<*> -> {
                Log.d(TAG, "Inside_Loading")
                Loader()
            }

            is ResourceState.Success<*> -> {
                val response = (newsResponse as ResourceState.Success).data
                Log.d(TAG, "Inside_Success ${response.status} = ${response.totalResults}")
                NewsList(response)
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