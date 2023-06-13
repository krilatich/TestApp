package com.example.data.mapper

import com.example.data.models.CategoriesDto
import com.example.data.models.DishesDto
import com.example.domain.models.Categories
import com.example.domain.models.Category
import com.example.domain.models.Dish
import com.example.domain.models.Dishes


internal fun dishesDtoToDishes(dishesDto: DishesDto): Dishes{
	val dataDishes = dishesDto.dishDtos
	val domainDishes = mutableListOf<Dish>()
	for( dish in dataDishes ){
		domainDishes.add(Dish(
			description = dish.description,
			name = dish.name,
			tegs = dish.tegs,
			image_url = dish.image_url,
			weight = dish.weight,
			price = dish.price,
			id = dish.id
		))
	}
	return Dishes(
		dishes = domainDishes.toList()
	)
}


internal fun categoriesDtoToCategories(categoriesDto: CategoriesDto): Categories{
	val dataCategories = categoriesDto.categoryDtos
	val domainCategories = mutableListOf<Category>()
	for( category in dataCategories ){
		domainCategories.add(Category(
			id = category.id,
			name = category.name,
			image_url = category.image_url
		))
	}
	return Categories(
		categories = domainCategories.toList()
	)
}