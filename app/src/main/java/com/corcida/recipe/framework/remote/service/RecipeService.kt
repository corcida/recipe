package com.corcida.recipe.framework.remote.service

import com.corcida.recipe.framework.remote.model.RecipeResult
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes")
    suspend fun getAllRecipes(): RecipeResult
}