package com.dicoding.asclepius.view.response



import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        fun getApiService(): ApiService {
            val client = createOkHttpClient()
            val retrofit = addRetrofit(client)
            return retrofit.create(ApiService::class.java)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val loggingInterceptor = if("true".toBoolean()) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestHeaders = original.newBuilder()
                        .header("Authorization", "275ac6dc23b34f0192664fcf9948382d")
                    val request = requestHeaders.build()
                    chain.proceed(request)
                }
                .addNetworkInterceptor(loggingInterceptor)
                .build()
            return client
        }

        private fun addRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}