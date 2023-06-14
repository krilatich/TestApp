package com.example.data.repository

import com.example.data.data_source.BasketDao
import com.example.domain.models.Dish
import com.example.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow


class BasketRepositoryImpl(private val dao: BasketDao) : BasketRepository {
    override suspend fun addDish(dish: Dish) {
        dao.insertDish(dish)
    }

    override suspend fun updateDish(dish: Dish) {
        dao.updateDish(dish)
    }

    override suspend fun deleteDish(dish: Dish) {
        dao.deleteDish(dish)
    }

    override suspend fun checkDishIsAdded(id: Int): Boolean {
        return dao.checkDish(id) > 0
    }

    override fun getDishes(): Flow<List<Dish>> {
        return dao.getDishes()
    }
}