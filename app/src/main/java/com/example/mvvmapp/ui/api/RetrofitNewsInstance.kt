package com.example.mvvmapp.ui.api

import com.example.mvvmapp.ui.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNewsInstance {
    companion object{

        private val retrofitNews by lazy {
            // log responses of retrofit the dependency can be found in the build.gradle file
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy{
            retrofitNews.create(NewsApi::class.java)
        }
    }
}