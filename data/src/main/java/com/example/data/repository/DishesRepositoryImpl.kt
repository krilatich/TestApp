package com.example.data.repository

import com.example.data.api.TestAppApi
import com.example.data.mapper.dishesDtoToDishes
import com.example.domain.models.Dishes
import com.example.domain.repository.DishesRepository

class DishesRepositoryImpl(private val api:TestAppApi):DishesRepository {
    override suspend fun getDishes(): Dishes  = dishesDtoToDishes(api.getDishes())
}