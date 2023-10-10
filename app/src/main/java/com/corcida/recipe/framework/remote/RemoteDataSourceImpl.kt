package com.corcida.recipe.framework.remote

import com.corcida.data.source.RemoteDataSource
import com.corcida.domain.Recipe
import com.corcida.recipe.framework.toDomainRecipe

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun getRecipes(): List<Recipe> =
        RecipeApi.recipeService
            .getAllRecipes()
            .results.map { it.toDomainRecipe() }

}