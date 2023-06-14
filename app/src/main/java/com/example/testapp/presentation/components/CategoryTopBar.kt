package com.example.testapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testapp.R

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
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(10.dp)
                            )
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
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(10.dp)
                            )
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