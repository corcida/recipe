package com.corcida.recipe.ui.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.corcida.recipe.ui.screens.common.RecipesAppComposable
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    onNavigate: (Int) -> Unit,
    homeViewModel: HomeViewModel
) {
    val recipes = homeViewModel.recipes.collectAsState()
    homeViewModel.getRecipesData()

    RecipesAppComposable {
        Scaffold(
            topBar = { HomeAppBar() }
        ) { padding ->
            RecipeList(
                recipes = recipes.value,
                onClick = { onNavigate(it.id) },
                onFavoriteClick = { homeViewModel.toggleFavorite(it) },
                modifier = Modifier.padding(padding)
            )
        }

    }
}