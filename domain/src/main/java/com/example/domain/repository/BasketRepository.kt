package com.example.domain.repository

import com.example.domain.models.Dish

interface BasketRepository {
    suspend fun addDish(dish: Dish)
}
