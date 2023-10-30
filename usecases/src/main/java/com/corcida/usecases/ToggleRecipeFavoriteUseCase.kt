package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.domain.Recipe

class ToggleRecipeFavoriteUseCase (private val recipesRepository: RecipesRepository) {
    suspend fun invoke(recipe: Recipe) : Recipe = with(recipe) {
        copy(favorite = !favorite).also { recipesRepository.update(it) }
    }
}