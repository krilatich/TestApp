package com.example.domain.repository

import com.example.domain.models.Categories

interface CategoryRepository {
    suspend fun getCategories(): Categories
}
