package com.tm.newsinshort.data.api

import com.tm.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "f28a292bdcbf4ec1a36c2fa6160c4146"//"YOUR_API_KEY"
    ): Response<NewsResponse>
}