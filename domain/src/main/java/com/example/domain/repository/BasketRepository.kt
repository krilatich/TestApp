package com.example.domain.repository

import com.example.domain.models.Dish
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun addDish(dish: Dish)
    suspend fun checkDishIsAdded(id: Int): Boolean
    fun getDishes(): Flow<List<Dish>>
    suspend fun deleteDish(dish: Dish)
    suspend fun updateDish(dish: Dish)
}
