package com.example.domain.usecase

import com.example.domain.models.Categories
import com.example.domain.models.Resource
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCategoriesUseCase(private val repository: CategoryRepository) {

    operator fun invoke(): Flow<Resource<Categories>> = flow {
        try {
            emit(Resource.Loading<Categories>())
            val categories = repository.getCategories()
            emit(Resource.Success<Categories>(categories))
        } catch (e: IOException) {
            emit(Resource.Error<Categories>("Не удалось подключиться к серверу. Проверьте интернеты"))
        } catch (e: Exception) {
            emit(Resource.Error<Categories>(e.localizedMessage ?: "Произошла ошибка"))
        }

    }
}