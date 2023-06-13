package com.example.data.repository

import com.example.data.api.TestAppApi
import com.example.data.mapper.categoriesDtoToCategories
import com.example.domain.models.Categories
import com.example.domain.repository.CategoryRepository

class CategoriesRepositoryImpl(private val api:TestAppApi):CategoryRepository {
    override suspend fun getCategories(): Categories  = categoriesDtoToCategories(api.getCategories())
}