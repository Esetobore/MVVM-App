package com.example.mvvmapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.fragments.DiagnosisFragment
import com.example.mvvmapp.ui.fragments.DoctorsFragment
import com.example.mvvmapp.ui.fragments.NewsFragment
import com.example.mvvmapp.ui.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Array.newInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val newsNavFragment = findViewById<View>(R.id.newsNavHostFragment)
        bottomNavigationView.setupWithNavController(newsNavFragment.findNavController())


    }
}