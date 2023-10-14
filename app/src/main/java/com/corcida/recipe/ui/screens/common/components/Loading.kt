package com.corcida.recipe.ui.screens.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Loading(){
    Box (
        modifier = Modifier
            .fillMaxSize()
    ){
        CircularProgressIndicator(
            trackColor = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}