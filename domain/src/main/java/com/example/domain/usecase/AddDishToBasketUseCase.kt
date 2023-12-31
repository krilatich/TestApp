package com.example.domain.usecase

import com.example.domain.models.Dish
import com.example.domain.repository.BasketRepository

class AddDishToBasketUseCase(private val repository: BasketRepository) {
    suspend operator fun invoke(dish: Dish) {
        repository.addDish(dish)
    }
}