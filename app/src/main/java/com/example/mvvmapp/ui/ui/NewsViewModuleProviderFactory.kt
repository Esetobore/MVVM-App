package com.example.mvvmapp.ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.ui.repository.NewsRepository

class NewsViewModuleProviderFactory(
     val newsRepository: NewsRepository
 ) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return NewsViewModule(newsRepository) as T
     }

}