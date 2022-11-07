package com.yape.recipe.domain.uc

import com.yape.recipe.data.database.entities.toDatabase
import com.yape.recipe.data.repository.RecipeRepository
import com.yape.recipe.domain.model.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {

    suspend operator fun invoke():List<Recipe>{
        val recipes = repository.getRecipes()
        return if(recipes.isNotEmpty()){
            repository.clearRecipes()
            repository.insertRecipes(recipes.map { it.toDatabase() })
            recipes
        }else{
            repository.getRecipesFromDatabase()
        }
    }

}