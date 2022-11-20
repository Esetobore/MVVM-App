package com.example.mvvmapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.MainActivity
import com.example.mvvmapp.ui.adapter.NewsAdapter
import com.example.mvvmapp.ui.utils.Resource
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment: BaseFragment() {

    lateinit var newsAdapter : NewsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        // handling response and updating information in our recycler view from the viewModel live data
        viewModel.newsModel.observe(viewLifecycleOwner, Observer { newsresponse ->
            when(newsresponse){
                is Resource.Success ->{
                    hideProgressBar()
                    newsresponse.data?.let {
                            newsAdapter.diff.submitList(it.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    newsresponse.message?.let {
                        Log.e("NewsFragment","Error: $it")
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        rvCurrentNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }
}