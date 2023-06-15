package com.example.testapp.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.testapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_display_bold)),
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
        fontWeight = FontWeight.W500,
        fontSize = 10.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_display_bold)),
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_pro_display_bold)),
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)