package com.corcida.recipe.framework.remote.service

import com.corcida.recipe.framework.remote.model.RecipeResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes")
    suspend fun getAllRecipes(): RecipeResult
}