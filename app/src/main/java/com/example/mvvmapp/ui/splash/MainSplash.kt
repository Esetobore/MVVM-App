package com.example.mvvmapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.ui.fragments.Login
import kotlinx.android.synthetic.main.activity_main_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash)

        lottie2.animate().translationY(1500f).setDuration(1000).startDelay = 5000

        lifecycleScope.launch(Dispatchers.Default){
            intent()
        }
    }
    private suspend fun intent(){
        delay(6000)
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}