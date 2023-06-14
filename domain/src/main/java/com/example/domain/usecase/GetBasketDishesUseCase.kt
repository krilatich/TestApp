package com.example.domain.usecase

import com.example.domain.models.Dish
import com.example.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow

class GetBasketDishesUseCase(private val repository: BasketRepository) {

    operator fun invoke(): Flow<List<Dish>> = repository.getDishes()
}