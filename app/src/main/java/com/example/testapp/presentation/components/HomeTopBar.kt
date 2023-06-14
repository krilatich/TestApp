package com.example.testapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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