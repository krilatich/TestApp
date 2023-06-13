package com.example.data.models

import com.google.gson.annotations.SerializedName

data class DishesDto(
    @SerializedName("dishes")
    val dishDtos: List<DishDto>
)