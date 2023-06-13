package com.example.testapp.presentation.home

import com.example.domain.models.Category

data class HomeState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)
