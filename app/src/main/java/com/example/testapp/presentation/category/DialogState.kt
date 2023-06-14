package com.example.testapp.presentation.category

import com.example.domain.models.Dish

data class DialogState(
    val isOpen: Boolean = false,
    val dish: Dish = Dish(),
    val isLoading: Boolean = false,
    val isAdded: Boolean = false
)
