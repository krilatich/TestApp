package com.example.testapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapp.R


sealed class NavigationItem(
    val route: String?,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Home : NavigationItem("home_screen", R.string.home_screen_route, R.drawable.home)
    object Search : NavigationItem(null, R.string.search_screen_route, R.drawable.search)
    object Basket : NavigationItem("basket_screen", R.string.basket_screen_route, R.drawable.basket)
    object Account : NavigationItem(null, R.string.account_screen_route, R.drawable.account)
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Basket,
        NavigationItem.Account,
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Blue
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(
                            id = item.labelResId
                        )
                    )
                },
                label = {
                    Text(
                        text = stringResource(
                            id = item.labelResId
                        )
                    )
                },
                selected = currentRoute == item.route,
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                onClick = {
                    if (item.route != null)
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                }
            )
        }
    }
}
