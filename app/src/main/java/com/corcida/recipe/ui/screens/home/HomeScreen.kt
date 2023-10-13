package com.corcida.recipe.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corcida.recipe.ui.screens.common.RecipesAppComposable

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    onNavigate: (Int) -> Unit,
    homeViewModel: HomeViewModel
) {
    val recipes = homeViewModel.recipes.collectAsState()
    val searchText by homeViewModel.searchText.collectAsState()

    homeViewModel.getRecipesData()

    RecipesAppComposable {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TextField(
                    value = searchText,
                    onValueChange = homeViewModel::queryRecipe,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    placeholder = { Text(text = "Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            modifier = Modifier.clickable { homeViewModel.queryRecipe("") }
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                RecipeList(
                    recipes = recipes.value,
                    onClick = { onNavigate(it.id) },
                    onFavoriteClick = { homeViewModel.toggleFavorite(it) },
                    modifier = Modifier.padding(padding)
                )
            }

        }

    }
}