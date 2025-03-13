package com.tm.newsinshort.data.datasource

import com.tm.newsinshort.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    suspend fun getNewsHeadline(country: String): Response<NewsResponse>
}