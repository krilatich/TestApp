package com.example.testapp.presentation.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Dish
import com.example.testapp.R
import com.example.testapp.presentation.components.BottomNavigationBar
import com.example.testapp.presentation.components.HomeTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(navController: NavController, basketViewModel: BasketViewModel = koinViewModel()) {

    val state = basketViewModel.state.value
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = { HomeTopBar(date = basketViewModel.getDate()) }) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            items(state.basketDishes.size) { i ->
                BasketDish(state.basketDishes[i],
                    onDecreaseButtonClick = { basketViewModel.decreaseDish(state.basketDishes[i]) },
                    onIncreaseButtonClick = { basketViewModel.increaseDish(state.basketDishes[i]) }
                )
            }
        }
        Box(
            Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
            ) {
                Text(
                    "Оплатить " + state.totalPrice,
                    style = MaterialTheme.typography.headlineSmall
                )
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
}

@Composable
fun BasketDish(
    dish: Dish,
    onIncreaseButtonClick: () -> Unit,
    onDecreaseButtonClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Box(
                Modifier
                    .size(62.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(10.dp)
                    )
            ) {
                Image(
                    rememberAsyncImagePainter(model = dish.image_url),
                    contentDescription = dish.name,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.width(100.dp)
            ) {
                Text(dish.name, style = MaterialTheme.typography.bodyMedium)
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
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp)
                .width(100.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus_sign),
                contentDescription = "plus",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onDecreaseButtonClick()
                    }
            )
            Text(text = dish.count.toString(), style = MaterialTheme.typography.bodyMedium)
            Icon(
                painter = painterResource(id = R.drawable.plus_sign),
                contentDescription = "plus",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onIncreaseButtonClick()
                    }
            )
        }
    }
}
