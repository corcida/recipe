package com.corcida.recipe.di

import com.corcida.data.repository.RecipesRepository
import com.corcida.usecases.FindRecipe
import com.corcida.usecases.GetRecipes
import com.corcida.usecases.ToggleRecipeFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun getRecipesUseCase(
        repository: RecipesRepository
    ) = GetRecipes(repository)

    @Provides
    fun findRecipeUseCase(
        repository: RecipesRepository
    ) = FindRecipe(repository)

    @Provides
    fun toggleRecipeFavoriteUseCase(
        repository: RecipesRepository
    ) = ToggleRecipeFavorite(repository)
}