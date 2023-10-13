package com.corcida.recipe.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.corcida.domain.Recipe
import com.corcida.recipe.ui.screens.common.ScopeViewModel
import com.corcida.usecases.GetRecipes
import com.corcida.usecases.ToggleRecipeFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val getRecipes: GetRecipes,
    private val toggleRecipeFavorite: ToggleRecipeFavorite
) : ScopeViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading(false))
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val originalRecipes = MutableStateFlow<List<Recipe>>(listOf())
    private val _recipes = MutableStateFlow<List<Recipe>>(listOf())
    val recipes : StateFlow<List<Recipe>> = searchText
        .debounce(100L)
        .onEach { _uiState.value = HomeUiState.Loading(true) }
        .combine(originalRecipes) { text, persons ->
            if(text.isBlank()) {
                persons
            } else {
                delay(200L)
                persons.filter {
                    filterRecipe(text, it)
                }
            }
        }
        .onEach { HomeUiState.Loading(false) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _recipes.value
        )

    init {
        initScope()
    }
    fun getRecipesData() = launch {
        _uiState.value = HomeUiState.Loading(true)
        getRecipes.invoke().collect {
            originalRecipes.value = it
            _recipes.value = it
        }
    }

    fun queryRecipe(query : String?) {
        _searchText.value = query?:""
    }

    fun toggleFavorite(recipe: Recipe){
        launch {
            toggleRecipeFavorite.invoke(recipe)
        }
    }

    private fun filterRecipe(query: String?, recipe: Recipe) : Boolean{
        return when {
            query.isNullOrEmpty() -> true
            recipe.title.lowercase().contains(query.lowercase()) -> true
            recipe.tags.contains(query) -> true
            recipe.ingredients.contains(query) -> true
            else -> false
        }
    }

}