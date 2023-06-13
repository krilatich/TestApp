package com.example.testapp.di

import com.example.data.api.TestAppApi
import com.example.data.data_source.BasketDao
import com.example.data.data_source.BasketDatabase
import com.example.data.repository.BasketRepositoryImpl
import com.example.data.repository.CategoriesRepositoryImpl
import com.example.data.repository.DishesRepositoryImpl
import com.example.domain.repository.BasketRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.DishesRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestAppApi::class.java)
    }

    single<BasketRepository> {
        BasketRepositoryImpl(get())
    }

    single <BasketDao> {
        get<BasketDatabase>().basketDao()
    }

    single { BasketDatabase.getInstance(get()) }

    single { get<BasketDatabase>().basketDao() }




    single<DishesRepository> {
        DishesRepositoryImpl(get())
    }

    single<CategoryRepository> {
        CategoriesRepositoryImpl(get())
    }
}