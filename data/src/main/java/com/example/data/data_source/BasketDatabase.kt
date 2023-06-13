package com.example.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.models.Dish

@Database(entities = [Dish::class], version = 1)
abstract class BasketDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao

    companion object {
        @Volatile
        private var instance: BasketDatabase? = null

        fun getInstance(context: Context): BasketDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BasketDatabase::class.java,
                        "basket_db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}