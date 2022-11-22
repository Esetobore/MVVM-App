package com.example.mvvmapp.ui.repository

import com.example.mvvmapp.ui.api.RetrofitNewsInstance

class NewsRepository(

) {
        suspend fun getNewsView(countryCode: String, pageNumber: Int)=
            RetrofitNewsInstance.api.getNews(countryCode,pageNumber)

    suspend fun  searchNews(query: String, pageNumber: Int)=
        RetrofitNewsInstance.api.searchNews(query,pageNumber)
}