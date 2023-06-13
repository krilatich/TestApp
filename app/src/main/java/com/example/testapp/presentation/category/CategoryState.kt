package com.example.testapp.presentation.category

import com.example.domain.models.Dish

data class CategoryState(
    val isLoading: Boolean = false,
    val dishes: List<Dish> = emptyList(),
    val tags: List<String> = emptyList(),
    val chosenTag: String = "Все меню",
    val error: String = ""
)
