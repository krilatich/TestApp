package com.example.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapp.presentation.basket.BasketScreen
import com.example.testapp.presentation.category.CategoryScreen
import com.example.testapp.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.BasketScreen.route) {
            BasketScreen(navController = navController)
        }

        composable("${Screen.CategoryScreen.route}/{categoryName}",
            arguments = listOf(
                navArgument("categoryName") { type = NavType.StringType }
            )) {
            val categoryName = it.arguments?.getString("categoryName")!!
            CategoryScreen(navController = navController, categoryName = categoryName)
        }

    }
}