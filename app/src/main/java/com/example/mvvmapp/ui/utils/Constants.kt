package com.example.mvvmapp.ui.utils

import android.content.Context
import android.widget.Toast

class Constants {
    companion object{
        const val API_KEY = "20f165f7c3434d34a1adff23be19de44"
        const val BASE_URL = "https://newsapi.org/"
        const val SEARCH_VIEW_DELAY = 600L

    }
    object Utils {
        fun showToast(mContext: Context?, message: String?) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}