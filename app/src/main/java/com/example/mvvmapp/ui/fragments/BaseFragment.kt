package com.example.mvvmapp.ui.fragments

import android.view.View
import androidx.fragment.app.Fragment
import com.example.mvvmapp.ui.uimodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

abstract class BaseFragment : Fragment() {
    fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }
    fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    lateinit var viewModel: NewsViewModel


}