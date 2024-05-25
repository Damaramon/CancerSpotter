package com.dicoding.asclepius.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.dicoding.asclepius.view.response.ApiConfig
import com.dicoding.asclepius.view.response.ApiResult
import com.dicoding.asclepius.view.response.ArticlesRepository

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var adapter: ListNewsAdapter
    private val articlesRepository by lazy { ArticlesRepository.getInstance(ApiConfig.getApiService()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        adapter = ListNewsAdapter()
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.adapter = adapter

        // Fetch and observe articles using coroutines
        lifecycleScope.launch {
            articlesRepository.getListArticles().collectLatest { articles ->
                when (articles) {
                    is ApiResult.Success -> {
                        adapter.setData(articles.data) // Update adapter with data
                    }
                    is ApiResult.Error -> {
                        Log.e("NewsActivity", "Error fetching articles: ${articles.error}")
                    }
                    is ApiResult.Loading -> {
                        // Show loading indicator (optional)
                    }
                    is ApiResult.Empty -> {
                        // Handle empty list case (optional)
                    }
                }
            }
        }
    }
}