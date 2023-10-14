package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.Flow

class GetRecipes (private val recipesRepository: RecipesRepository) {
    suspend fun invoke(): Flow<List<Recipe>> {
        if (recipesRepository.isDatabaseEmpty()){
            try{
                val recipes = recipesRepository.getRecipesFromServer()
                recipesRepository.saveRecipes(recipes)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
        return recipesRepository.getRecipesFromDatabase()
    }
}