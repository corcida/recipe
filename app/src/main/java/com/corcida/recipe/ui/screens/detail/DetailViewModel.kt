package com.corcida.recipe.ui.screens.detail

import com.corcida.domain.Recipe
import com.corcida.recipe.ui.screens.common.ScopeViewModel
import com.corcida.usecases.FindRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val getRecipe: FindRecipeUseCase
) : ScopeViewModel() {

    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> = _recipe

    init {
        initScope()
    }

    fun findRecipeById(id: Int){
        launch {
            _recipe.value = getRecipe.invoke(id)
        }
    }

}