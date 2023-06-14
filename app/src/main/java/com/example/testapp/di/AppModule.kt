package com.example.testapp.di

import com.example.testapp.presentation.basket.BasketViewModel
import com.example.testapp.presentation.category.CategoryViewModel
import com.example.testapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        CategoryViewModel(get(), get(), get())
    }
    viewModel {
        BasketViewModel(get(), get(), get())
    }
}