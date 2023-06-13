package com.example.data.repository

import com.example.data.data_source.BasketDao
import com.example.domain.models.Dish
import com.example.domain.repository.BasketRepository

class BasketRepositoryImpl(private val dao:BasketDao):BasketRepository {
    override suspend fun addDish(dish: Dish) {
        dao.insertDish(dish)
    }
}