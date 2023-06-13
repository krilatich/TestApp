package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("сategories")
    val categoryDtos: List<CategoryDto>
)