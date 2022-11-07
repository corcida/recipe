package com.yape.recipe.ui.vm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.yape.recipe.domain.model.Recipe
import com.yape.recipe.domain.uc.GetRecipesUseCase
import com.yape.recipe.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val gson: Gson
): ViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model : LiveData<UiModel> get() = _model
    private var recipes: List<Recipe> = emptyList()

    sealed class UiModel{
        class Error(val value: String) : UiModel()
        class RecipeData(val data: List<Recipe>) : UiModel()
        class RecipeSelected(val data: String, val view: View) : UiModel()
    }

    fun onCreate(){
        setLoadingData()
        getRecipes()
    }

    private fun setLoadingData()= viewModelScope.launch (Dispatchers.Main){
        _model.value = UiModel.RecipeData(listOf(Recipe(Constants.loading),
            Recipe(Constants.loading),
            Recipe(Constants.loading), Recipe(Constants.loading)))
    }

    private fun getRecipes() = viewModelScope.launch {
        val result = getRecipesUseCase()
        withContext(Dispatchers.Main) {
            if (!result.isNullOrEmpty()) {
                recipes = result
                _model.value = UiModel.RecipeData(result)
            } else {
                _model.value = UiModel.Error("You currently have no data. " +
                        "Check your internet connection and try again!")
            }
        }
    }

    fun filterRecipe(title: String){
        val filteredResults = if (title.isEmpty()) recipes.filter { it.title.contains(title) } else recipes
        _model.value = UiModel.RecipeData(filteredResults)
    }

    fun onMovieItemSelected(recipe: Recipe, view: View){
        _model.value = UiModel.RecipeSelected(gson.toJson(recipe), view)
    }

}