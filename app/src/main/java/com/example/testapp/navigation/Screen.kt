package com.example.testapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object CategoryScreen: Screen("category_screen")
    object BasketScreen: Screen("basket_screen")
}