package com.example.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


private val LightColors = lightColorScheme(
    primary = AppColors.primaryLight,
    onPrimary = AppColors.onPrimaryLight,
    secondary = AppColors.secondaryLight,
    onSecondary = AppColors.onSecondaryLight,
    background = AppColors.surfaceLight,
    onBackground = AppColors.onSurfaceLight,
    surface = AppColors.surfaceLight,
    onSurface = AppColors.onSurfaceLight
)

private val DarkColors = darkColorScheme(
    primary = AppColors.primaryDark,
    onPrimary = AppColors.onPrimaryDark,
    secondary = AppColors.secondaryDark,
    onSecondary = AppColors.onSecondaryDark,
    background = AppColors.surfaceDark,
    onBackground = AppColors.onSurfaceDark,
    surface = AppColors.surfaceDark,
    onSurface = AppColors.onSurfaceDark
)


@Composable
fun DIGIWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorScheme = if(darkTheme) DarkColors else LightColors

    CompositionLocalProvider(){
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

