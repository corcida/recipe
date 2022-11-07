package com.yape.recipe.data.network

import com.yape.recipe.data.model.RecipeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RecipeService {

    @Headers("Content-Type: application/json")
    @GET("recipes")
    suspend fun getRecipes(): Response<RecipeData>
}