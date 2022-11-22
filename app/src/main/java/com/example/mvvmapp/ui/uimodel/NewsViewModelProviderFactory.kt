package com.example.mvvmapp.ui.uimodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.ui.repository.NewsRepository

class NewsViewModelProviderFactory(
     val newsRepository: NewsRepository,
     val appVMPF : Application
 ) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return NewsViewModel(newsRepository,appVMPF) as T
     }

}