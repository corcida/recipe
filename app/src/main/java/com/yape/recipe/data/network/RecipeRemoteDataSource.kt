package com.yape.recipe.data.network

import javax.inject.Inject

class RecipeRemoteDataSource @Inject constructor(
    private val service: RecipeService
): BaseDataSource() {

    suspend fun getRecipes() = getResult { service.getRecipes() }

}