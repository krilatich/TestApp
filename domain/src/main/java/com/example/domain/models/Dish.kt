package com.example.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dish(
    val description: String,
    @PrimaryKey val id: Int,
    val image_url: String,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)