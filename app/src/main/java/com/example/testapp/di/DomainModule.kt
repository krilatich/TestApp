package com.example.testapp.di

import com.example.domain.usecase.AddDishToBasketUseCase
import com.example.domain.usecase.CheckDishIsAddedUseCase
import com.example.domain.usecase.DeleteDishUseCase
import com.example.domain.usecase.GetBasketDishesUseCase
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.domain.usecase.GetDishesUseCase
import com.example.domain.usecase.UpdateAmountDishUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetDishesUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { AddDishToBasketUseCase(get()) }
    factory { CheckDishIsAddedUseCase(get()) }
    factory { UpdateAmountDishUseCase(get()) }
    factory { GetBasketDishesUseCase(get()) }
    factory { DeleteDishUseCase(get()) }
}