package com.example.domain.usecase

import com.example.domain.repository.BasketRepository

class CheckDishIsAddedUseCase(private val repository: BasketRepository) {

    suspend operator fun invoke(id: Int): Boolean {
        return repository.checkDishIsAdded(id)
    }
}