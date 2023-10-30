package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.domain.Location
import com.corcida.domain.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class ToggleRecipeFavoriteUseCaseTest {

    @Test
    fun `When toggle recipe favorite is invoked then favorite value on recipe is changed`()  {
        //Given
        val recipe = Recipe(1, "test 1", "", "", listOf(), listOf(),
            Location("", 0.0, 0.0), false )
        val repository = mock<RecipesRepository>()
        val toggleRecipeFavoriteUseCase = ToggleRecipeFavoriteUseCase(repository)

        //When
        val response = runBlocking { toggleRecipeFavoriteUseCase.invoke(recipe) }

        //Then
        assert(response.favorite != recipe.favorite)
    }

    @Test
    fun `When toggle recipe favorite is invoked then repository update recipe is called`()  {
        //Given
        val recipe = Recipe(1, "test 1", "", "", listOf(), listOf(),
            Location("", 0.0, 0.0), false )
        val repository = mock<RecipesRepository>()
        val toggleRecipeFavoriteUseCase = ToggleRecipeFavoriteUseCase(repository)

        //When
        val recipeChanged = runBlocking { toggleRecipeFavoriteUseCase.invoke(recipe) }

        //Then
        verifyBlocking(repository) { update(recipeChanged) }
    }

}