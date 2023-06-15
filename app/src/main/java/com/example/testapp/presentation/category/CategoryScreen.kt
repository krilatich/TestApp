package com.example.testapp.presentation.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Dish
import com.example.testapp.presentation.components.BottomNavigationBar
import com.example.testapp.presentation.components.CategoryTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    categoryName: String,
    categoryViewModel: CategoryViewModel = koinViewModel()
) {
    val state = categoryViewModel.state.value
    val dishDialogState = categoryViewModel.dishDialogState.collectAsState()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = {
            CategoryTopBar(
                categoryName = categoryName,
                onBackButtonClick = { navController.navigate("home_screen") },
                chosenTag = state.chosenTag,
                tags = state.tags,
                onTagClick = {
                    categoryViewModel.getDishes(it)
                }
            )
        }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), modifier = Modifier
                .padding(paddingValues = it)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            items(state.dishes.size) { index ->
                Dish(state.dishes[index], onClick = { dish ->
                    categoryViewModel.onDishClicked(dish)
                })
            }
        }
        if (state.isLoading)
            Box(
                modifier = Modifier
                    .alpha(0.5f)
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        if (state.error.isNotEmpty())
            Box(
                modifier = Modifier
                    .alpha(0.5f)
                    .fillMaxSize(),
            ) {
                Text(
                    state.error,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
    }
    if (dishDialogState.value.isOpen)
        DishDialog(
            dishDialogState.value.dish,
            onDishDialogDismiss = { categoryViewModel.onDishDialogDismiss() },
            onAddDishClicked = { categoryViewModel.onAddDishClicked(dishDialogState.value.dish) },
            isAdded = dishDialogState.value.isAdded
        )
}

@Composable
fun Dish(dish: Dish, onClick: (Dish) -> Unit) {
    Column {
        Card(modifier = Modifier
            .size(110.dp)
            .clickable {
                onClick(dish)
            }) {
            Box(Modifier.fillMaxSize()) {
                Image(
                    rememberAsyncImagePainter(model = dish.image_url),
                    contentDescription = dish.name,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        Text(
            dish.name, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .width(110.dp)
        )
    }
}


@Composable
fun DishDialog(
    dish: Dish, onAddDishClicked: () -> Unit,
    onDishDialogDismiss: () -> Unit,
    isAdded: Boolean
) {
    Box {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.5f))
                .clickable(onClick = onDishDialogDismiss)
        )
        Box(
            Modifier
                .align(Alignment.Center)
                .padding(20.dp)
                .background(Color.White, RoundedCornerShape(15.dp))
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        rememberAsyncImagePainter(model = dish.image_url),
                        contentDescription = dish.name,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center)
                    )
                }
                Text(
                    dish.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Row {
                    Text(
                        dish.price.toString() + " ₽ ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        "· " + dish.weight.toString() + "г",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(dish.description, style = MaterialTheme.typography.bodyMedium)
                Button(
                    onClick = {
                        if (!isAdded)
                            onAddDishClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    if (isAdded)
                        Text(
                            "Добавлено в корзину",
                            color = Color.Green,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    else
                        Text(
                            "Добавить в корзину",
                            style = MaterialTheme.typography.headlineSmall
                        )
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    Modifier
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .size(40.dp)
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "addToFavorites",
                        modifier = Modifier
                            .clickable(onClick = { })
                            .align(Alignment.Center)
                            .size(15.dp)
                    )
                }
                Box(
                    Modifier
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .size(40.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "closeDialog",
                        modifier = Modifier
                            .clickable(onClick = onDishDialogDismiss)
                            .align(Alignment.Center)
                            .size(15.dp)
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun CategoryPreview() {
    CategoryScreen(rememberNavController(), "Азиатская кухня")
}