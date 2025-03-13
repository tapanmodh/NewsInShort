package com.tm.newsinshort.ui.repository

import com.tm.newsinshort.data.datasource.NewsDataSource
import com.tm.newsinshort.data.entity.NewsResponse
import com.tm.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadline(country)

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error fetching news data"))
            }
        }.catch { error ->
            emit(ResourceState.Error(error.localizedMessage ?: "Some error in flow"))
        }
    }
}