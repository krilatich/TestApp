package com.example.domain.usecase

import com.example.domain.models.Dish
import com.example.domain.models.Resource
import com.example.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddDishToBasketUseCase(private val repository: BasketRepository) {

    operator fun invoke(dish: Dish): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>())
            val categories = repository.addDish(dish)
            emit(Resource.Success<Unit>(categories))
        }
         catch (e: Exception) {
            emit(Resource.Error<Unit>(e.localizedMessage ?: "Произошла ошибка"))
        }

    }
}