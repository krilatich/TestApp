package com.example.data.data_source

import androidx.room.*
import com.example.domain.models.Dish
import kotlinx.coroutines.flow.Flow


@Dao
interface BasketDao {

    @Query("SELECT * FROM dish")
    fun getDishes(): Flow<List<Dish>>

    @Query("SELECT COUNT() FROM dish WHERE id = :id")
    suspend fun checkDish(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: Dish)

    @Update
    suspend fun updateDish(dish: Dish)

    @Delete
    suspend fun deleteDish(dish: Dish)
}