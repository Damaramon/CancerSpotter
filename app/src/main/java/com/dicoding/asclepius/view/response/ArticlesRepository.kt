package com.dicoding.asclepius.view.response


import com.dicoding.asclepius.data.remote.response.ArticlesItem

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticlesRepository private constructor (
    private val apiService: ApiService
) {

    fun getListArticles() : Flow<ApiResult<List<ArticlesItem>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response =  apiService.getArticles(
                country = "cancer",
                category = "health",
                language = "en",
                apiKey = "275ac6dc23b34f0192664fcf9948382d"
            )
            if(response.articles.isEmpty()) {
                emit(ApiResult.Empty)
            } else {
                emit(ApiResult.Success(response.articles)) }
        } catch (e : Exception) {
            emit(ApiResult.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: ArticlesRepository? = null
        fun getInstance(
            apiService: ApiService
        ) : ArticlesRepository =
            instance ?: synchronized(this) {
                instance ?: ArticlesRepository(apiService)
            }.also {instance = it}
    }
}