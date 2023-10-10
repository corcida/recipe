package com.corcida.data.source

import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveRecipes(recipes: List<Recipe>)
    suspend fun getRecipes(): Flow<List<Recipe>>
    suspend fun findRecipe(id: Int): Recipe
    suspend fun update(recipe: Recipe)

}