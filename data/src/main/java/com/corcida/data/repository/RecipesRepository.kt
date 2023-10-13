package com.corcida.data.repository

import com.corcida.data.source.LocalDataSource
import com.corcida.data.source.RemoteDataSource
import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.Flow

class RecipesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun isDatabaseEmpty() = localDataSource.isEmpty()

    suspend fun getRecipesFromServer(): List<Recipe>{
        return remoteDataSource.getRecipes()
    }

    suspend fun saveRecipes(recipes: List<Recipe>) = localDataSource.saveRecipes(recipes)

    fun getRecipesFromDatabase() = localDataSource.getRecipes()

    suspend fun findRecipe(id: Int): Recipe = localDataSource.findRecipe(id)

    suspend fun update(recipe: Recipe) = localDataSource.update(recipe)

}