package com.example.mvvmapp.ui.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_news_web_view.*

class NewsWebView: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_web_view, container, false)
    }
    private val args: com.example.mvvmapp.ui.ui.fragments.NewsWebViewArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val article = args.news
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
}