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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Dish
import com.example.testapp.R
import com.example.testapp.presentation.components.BottomNavigationBar
import com.example.testapp.presentation.theme.cardBackground
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
        if (dishDialogState.value.isOpen)
            DishDialog(
                dishDialogState.value.dish!!,
                onDishDialogDismiss = { categoryViewModel.onDishDialogDismiss() },
                onAddDishClicked = { categoryViewModel.onAddDishClicked(dishDialogState.value.dish!!) },
                isAdded = dishDialogState.value.isAdded
            )

    }
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
    Box(
        Modifier
            .fillMaxSize()
            .alpha(0.5f)
    ) {
        Card(Modifier.align(Alignment.Center)) {
            Column(Modifier.padding(16.dp)) {
                Image(
                    rememberAsyncImagePainter(model = dish.image_url),
                    contentDescription = dish.name,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                )
                Text(dish.name)
                Row() {
                    Text(dish.price.toString() + " ₽ ")
                    Text(
                        "· " + dish.weight.toString() + "г",
                        color = Color.Gray
                    )
                }
                Text(dish.description)
                Button(
                    onClick = { onAddDishClicked() }, modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (isAdded)
                        Text("Добавлено в корзину")
                    else
                        Text("Добавить в корзину")
                }
            }
        }
    }
}

@Composable
fun CategoryTopBar(
    categoryName: String,
    onBackButtonClick: () -> Unit,
    chosenTag: String,
    tags: List<String>,
    onTagClick: (String) -> Unit
) {
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.back_button),
                contentDescription = "back_button",
                modifier = Modifier.clickable(onClick = onBackButtonClick)
            )
            Text(categoryName, style = MaterialTheme.typography.bodyLarge)
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(44.dp)
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 20.dp)
        ) {
            items(tags.size) {
                if (chosenTag == tags[it])
                    Text(
                        tags[it], modifier = Modifier
                            .background(Color.Blue, RoundedCornerShape(10.dp))
                            .padding(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                            .clickable {
                                onTagClick(tags[it])
                            }, color = Color.White
                    )
                else
                    Text(
                        tags[it], modifier = Modifier
                            .background(cardBackground, RoundedCornerShape(10.dp))
                            .padding(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 16.dp,
                                end = 16.dp
                            )

                            .clickable {
                                onTagClick(tags[it])
                            }, color = Color.Black
                    )
            }
        }
    }
}


@Composable
@Preview
fun CategoryPreview() {
    CategoryScreen(rememberNavController(), "Азиатская кухня")
}