package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.domain.Recipe

class FindRecipeUseCase(private val recipesRepository: RecipesRepository) {

    suspend fun invoke(id: Int): Recipe = recipesRepository.findRecipe(id)
}