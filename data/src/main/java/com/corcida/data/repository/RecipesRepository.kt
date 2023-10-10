package com.corcida.data.repository

import com.corcida.data.source.LocalDataSource
import com.corcida.data.source.RemoteDataSource
import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.Flow

class RecipesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun getRecipes(): Flow<List<Recipe>> {

        if (localDataSource.isEmpty()) {
            val recipes = remoteDataSource.getRecipes()
            localDataSource.saveRecipes(recipes)
        }

        return localDataSource.getRecipes()
    }

    suspend fun findRecipe(id: Int): Recipe = localDataSource.findRecipe(id)

    suspend fun update(recipe: Recipe) = localDataSource.update(recipe)

}