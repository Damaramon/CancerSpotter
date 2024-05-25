package com.dicoding.asclepius.view.response

import com.dicoding.asclepius.data.remote.response.ArticleResponse
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("q") country : String = "cancer",
        @Query("category") category : String = "health",
        @Query("language") language : String = "en",
        @Query("apiKey") apiKey : String
    ) : ArticleResponse<List<ArticlesItem>>

    @GET("articles")
    suspend fun getDetailArticles() : Call<ArticleResponse<List<ArticlesItem>>>
}