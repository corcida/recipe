package com.corcida.recipe.ui.screens.common.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.corcida.domain.Recipe
import com.corcida.recipe.R

@Composable
fun Thumb(recipe: Recipe) {
    Box(
        modifier = Modifier
            .height(dimensionResource(R.dimen.cell_thumb_height))
            .fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.image)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            onError = {
                Log.e("error", "data ${it.result.throwable}")
            }
        )

    }
}