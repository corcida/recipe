package com.yape.recipe.data.repository

import com.yape.recipe.data.database.dao.RecipeDao
import com.yape.recipe.data.database.entities.RecipeEntity
import com.yape.recipe.data.network.RecipeRemoteDataSource
import com.yape.recipe.domain.model.Recipe
import com.yape.recipe.domain.model.toDomain
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeDao
) {

    suspend fun getRecipes() : List<Recipe> {
        val data = remoteDataSource.getRecipes()
        return data.data?.results?.map { it.toDomain() } ?: emptyList()
    }

    suspend fun getRecipesFromDatabase():List<Recipe>{
        val response: List<RecipeEntity> = localDataSource.getRecipes()
        return response.map { it.toDomain() }
    }

    suspend fun insertRecipes(recipes:List<RecipeEntity>){
        localDataSource.insertAll(recipes)
    }

    suspend fun clearRecipes(){
        localDataSource.deleteAll()
    }

}