package com.corcida.recipe.ui.screens.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.corcida.recipe.R
import com.corcida.recipe.ui.screens.common.RecipesAppComposable
import com.corcida.recipe.ui.screens.common.components.ArrowBackIcon
import com.corcida.recipe.ui.screens.common.components.Thumb

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun DetailScreen(
    onNavigate: (Int) -> Unit,
    onBackPressed: () -> Unit,
    id: Int,
    detailViewModel: DetailViewModel
) {
    val recipe= detailViewModel.recipe.collectAsState()
    detailViewModel.findRecipeById(id)

    RecipesAppComposable {
        recipe.value?.let { recipeValue ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = recipeValue.title) },
                        navigationIcon = { ArrowBackIcon(onBackPressed) }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        onClick = { onNavigate.invoke(recipeValue.id) }
                    ){
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location Icon"
                        )
                    }
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    item {
                        Thumb(recipe = recipeValue)

                        Text(
                            text = recipeValue.overview,
                            modifier = Modifier
                                .padding(dimensionResource(R.dimen.padding_medium))
                        )

                        Text(
                            text = stringResource(R.string.ingredients),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(dimensionResource(R.dimen.padding_medium))
                        )

                        for (ingredient in recipeValue.ingredients){
                            Text(
                                text = "- $ingredient.",
                                modifier =
                                Modifier.padding(
                                    horizontal = dimensionResource(R.dimen.padding_medium)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


