package com.example.mvvmapp.ui.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmapp.ui.repository.NewsRepository

class NewsViewModule(
    val newsRepository : NewsRepository
): ViewModel() {

}