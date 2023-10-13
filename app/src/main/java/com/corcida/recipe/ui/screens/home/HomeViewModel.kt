package com.corcida.recipe.ui.screens.home

import android.util.Log
import com.corcida.domain.Recipe
import com.corcida.recipe.ui.screens.common.ScopeViewModel
import com.corcida.usecases.GetRecipes
import com.corcida.usecases.ToggleRecipeFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val getRecipes: GetRecipes,
    private val toggleRecipeFavorite: ToggleRecipeFavorite
) : ScopeViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState
    private val _recipes = MutableStateFlow<List<Recipe>>(listOf())
    private val originalRecipes = MutableStateFlow<List<Recipe>>(listOf())
    val recipes : StateFlow<List<Recipe>> = _recipes

    init {
        initScope()
    }
    fun getRecipesData() = launch {
        _uiState.value = HomeUiState.Loading
        getRecipes.invoke().collect {
            originalRecipes.value = it
            _recipes.value = it
        }
    }

    fun queryRecipe(query : String?) {
        launch {
            originalRecipes
                .map { it.filter { recipe -> filterRecipe(query, recipe) } }
                .collect {
                    _recipes.value = it
            }
        }
    }

    fun toggleFavorite(recipe: Recipe){
        launch {
            toggleRecipeFavorite.invoke(recipe)
        }
    }

    private fun filterRecipe(query: String?, recipe: Recipe) : Boolean{
        return when {
            query.isNullOrEmpty() -> true
            recipe.title.contains(query) -> true
            recipe.tags.contains(query) -> true
            recipe.ingredients.contains(query) -> true
            else -> false
        }
    }

}