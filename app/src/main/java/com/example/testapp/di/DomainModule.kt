package com.example.testapp.di

import com.example.data.data_source.BasketDao
import com.example.data.repository.BasketRepositoryImpl
import com.example.domain.repository.BasketRepository
import com.example.domain.usecase.AddDishToBasketUseCase
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.domain.usecase.GetDishesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetDishesUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { AddDishToBasketUseCase(get()) }
}