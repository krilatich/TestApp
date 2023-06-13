package com.example.domain.repository

import com.example.domain.models.Dishes

interface DishesRepository {
    suspend fun getDishes(): Dishes
}
