package com.example.testapp.presentation.basket

import com.example.domain.models.Dish

data class BasketState(
    val isLoading: Boolean = false,
    val basketDishes: List<Dish> = emptyList(),
    val totalPrice: String = "",
    val error: String = ""
)
