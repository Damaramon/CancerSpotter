package com.dicoding.asclepius.view
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.remote.response.ArticlesItem

import com.dicoding.asclepius.view.response.ApiResult
import com.dicoding.asclepius.view.response.ArticlesRepository
import kotlinx.coroutines.launch

class ArticleViewModel (private val articlesRepository: ArticlesRepository) : ViewModel() {
    val articleList: LiveData<ApiResult<List<ArticlesItem>>> by lazy {_articleList}
    private var _articleList = MutableLiveData<ApiResult<List<ArticlesItem>>>()

    fun fetchArticles() {
        viewModelScope.launch {
            articlesRepository.getListArticles().collect {result ->
                _articleList.postValue(result)
            }
        }
    }
}