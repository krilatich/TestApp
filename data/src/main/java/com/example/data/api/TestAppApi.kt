package com.example.data.api

import com.example.data.models.CategoriesDto
import com.example.data.models.DishesDto
import retrofit2.http.GET

interface TestAppApi {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): CategoriesDto

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun getDishes(): DishesDto
}