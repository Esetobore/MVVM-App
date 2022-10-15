package com.example.mvvmapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.ui.ui.NewsViewModule

abstract class BaseFragment : Fragment() {
    lateinit var viewModel: NewsViewModule


}