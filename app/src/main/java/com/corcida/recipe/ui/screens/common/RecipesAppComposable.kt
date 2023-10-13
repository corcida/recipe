package com.corcida.recipe.ui.screens.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.corcida.recipe.ui.theme.RecipeTheme

@Composable
fun RecipesAppComposable(content: @Composable () -> Unit) {
    RecipeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}