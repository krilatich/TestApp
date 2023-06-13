package com.example.data.data_source

import androidx.room.*
import com.example.domain.models.Dish
import kotlinx.coroutines.flow.Flow


@Dao
interface BasketDao {

    @Query("SELECT * FROM dish")
    fun getDishes(): Flow<List<Dish>>
/*
    @Query("SELECT * FROM dish WHERE id = :id")
    suspend fun getNoteById(id: Int): Dish?
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: Dish)

    @Delete
    suspend fun deleteDish(dish: Dish)
}