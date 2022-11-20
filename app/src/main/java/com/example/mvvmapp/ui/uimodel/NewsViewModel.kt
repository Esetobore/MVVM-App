package com.example.mvvmapp.ui.uimodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Contacts
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.ui.NewsResponse
import com.example.mvvmapp.ui.repository.NewsRepository
import com.example.mvvmapp.ui.utils.MVVMApplication
import com.example.mvvmapp.ui.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(val newsRepository : NewsRepository, app: Application): AndroidViewModel(app) {

    val newsModel: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val newsPage = 1
    init {
        getNews(countryCode = "us")
    }
    fun getNews(countryCode: String) = viewModelScope.launch {
        getNewCall(countryCode)
    }
    suspend fun getNewCall(countryCode: String){
        try {
            if(checkInternetConnection()){
                val response = newsRepository.getNewsView(countryCode, newsPage)
                newsModel.postValue(Resource.Loading())
                newsModel.postValue(handleNewsResponse(response))
            }else{
                newsModel.postValue(Resource.Error("No internet Connection"))
            }
        }catch (t:Throwable){
            when(t){
                is IOException -> newsModel.postValue(Resource.Error("Network Failure"))
                else -> newsModel.postValue(Resource.Error("Json Conversion Error"))
            }
        }
    }

    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun checkInternetConnection(): Boolean{
        val connectivityManager = getApplication<MVVMApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activityNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activityNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    Contacts.PhonesColumns.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}