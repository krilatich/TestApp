package com.example.testapp.presentation.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Category
import com.example.testapp.R
import com.example.testapp.presentation.components.BottomNavigationBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = koinViewModel()) {

    val state = homeViewModel.state.value
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = { HomeTopBar() }) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            items(state.categories.size) { i ->
                Category(state.categories[i], onClick = {
                    navController.navigate("category_screen/${state.categories[i].name}")
                })
            }
        }
    }
}


@Composable
fun Category(category: Category, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                rememberAsyncImagePainter(model = category.image_url),
                contentDescription = "categoryImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
                    .fillMaxWidth(0.5f)
            )
        }
    }
}

@Composable
fun HomeTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "location",
            )
            Column() {
                Text("Томск", style = MaterialTheme.typography.bodyLarge)
                Text(
                    "14 июня, 2023",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "avatar",
            modifier = Modifier
                .size(44.dp)
        )
    }
}

@Composable
@Preview
fun HomePreview() {
    HomeScreen(rememberNavController())
}