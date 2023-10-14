package com.corcida.recipe.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.corcida.domain.Location
import com.corcida.domain.Recipe
import com.corcida.recipe.R
import com.corcida.recipe.ui.screens.common.RecipesAppComposable
import com.corcida.recipe.ui.screens.common.components.Thumb

@ExperimentalFoundationApi
@Composable
fun RecipeList(
    recipes: List<Recipe>,
    onClick: (Recipe) -> Unit,
    onFavoriteClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
        modifier = modifier
    ) {
        items(
            items = recipes,
            key = { item ->  item.id }
        ){
            RecipeListItem(
                recipe = it,
                onClick = { onClick.invoke(it) },
                onFavoriteClick = { onFavoriteClick.invoke(it) },
                modifier = Modifier.padding(PaddingValues(dimensionResource(R.dimen.padding_medium))
                )
            )
        }
    }
}

@Composable
fun RecipeListItem(
    recipe: Recipe,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
        modifier = modifier.clickable { onClick() }
    ) {
        Column {
            Thumb(recipe)
            InformationBox(recipe, onFavoriteClick)
        }
    }
}

@Composable
private fun InformationBox(
    recipe: Recipe,
    onFavoriteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        val favorite =  recipe.favorite
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
            .padding(
                horizontal = dimensionResource(R.dimen.padding_xsmall)
            )
        ){
            Icon(
                imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onFavoriteClick.invoke() }
            )
        }
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Preview
@Composable
fun RecipeListPreview() {
    RecipesAppComposable {
        val recipe = Recipe(
            1,
            "Bomba rellena",
            "",
            "",
            listOf("ingredient 1", "ingredient 11"),
            listOf("tag 1", "tag 11"),
            Location("", 0.0, 0.0),
            false )
        RecipeListItem(recipe = recipe, onClick = {}, onFavoriteClick = {})
    }
}