package com.example.mvvmapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.repository.NewsRepository
import com.example.mvvmapp.ui.uimodel.NewsViewModel
import com.example.mvvmapp.ui.uimodel.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val newsNavFragment = findViewById<View>(R.id.newsNavHostFragment)
//        bottomNavigationView.setupWithNavController(newsNavFragment.findNavController())

       // val repository = NewsRepository(ArticleDatabase())
        val  viewModuleProviderFactory = NewsViewModelProviderFactory(NewsRepository())
        viewModel = ViewModelProvider(this, viewModuleProviderFactory)[NewsViewModel::class.java]
    }
}