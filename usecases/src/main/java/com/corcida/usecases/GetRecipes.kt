package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.domain.Recipe
import kotlinx.coroutines.flow.Flow

class GetRecipes (private val recipesRepository: RecipesRepository) {
    suspend fun invoke(id: Int): Flow<List<Recipe>> = recipesRepository.getRecipes()
}