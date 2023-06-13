package com.example.domain.usecase

import com.example.domain.models.Dishes
import com.example.domain.models.Resource
import com.example.domain.repository.DishesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception

class GetDishesUseCase(private val repository:DishesRepository) {

    operator fun invoke(): Flow<Resource<Dishes>> = flow {
        try {
            emit(Resource.Loading<Dishes>())
            val dishes = repository.getDishes()
            emit(Resource.Success<Dishes>(dishes))
        } catch (e: IOException) {
            emit(Resource.Error<Dishes>("Не удалось подключиться к серверу. Проверьте интернеты"))
        } catch (e: Exception) {
            emit(Resource.Error<Dishes>(e.localizedMessage ?: "Произошла ошибка"))
        }

    }
}