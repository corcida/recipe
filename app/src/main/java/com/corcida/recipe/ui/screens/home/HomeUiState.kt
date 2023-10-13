package com.corcida.recipe.ui.screens.home

import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.Flow

sealed class HomeUiState {
    object Loading : HomeUiState()
}