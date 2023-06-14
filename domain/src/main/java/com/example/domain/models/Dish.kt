package com.example.domain.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Dish(
    var description: String = "",
    @PrimaryKey var id: Int = 0,
    var image_url: String = "",
    var name: String = "",
    var price: Int = 0,
    @Ignore val tegs: List<String> = emptyList(),
    var weight: Int = 0,
    var count: Int = 1
)