package com.corcida.usecases

import com.corcida.data.repository.RecipesRepository
import com.corcida.data.source.LocalDataSource
import com.corcida.data.source.RemoteDataSource
import com.corcida.domain.Location
import com.corcida.domain.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class ToggleRecipeFavoriteTest {

    @Test
    fun `When toggle recipe favorite is invoked then favorite value on recipe is changed`()  {
        //Given
        val recipe = Recipe(1, "test 1", "", "", listOf(), listOf(),
            Location("", 0.0, 0.0), false )
        val repository = mock<RecipesRepository>()
        val toggleRecipeFavorite = ToggleRecipeFavorite(repository)

        //When
        val response = runBlocking { toggleRecipeFavorite.invoke(recipe) }

        //Then
        assert(response.favorite != recipe.favorite)
    }

    @Test
    fun `When toggle recipe favorite is invoked then repository update recipe is called`()  {
        //Given
        val recipe = Recipe(1, "test 1", "", "", listOf(), listOf(),
            Location("", 0.0, 0.0), false )
        val repository = mock<RecipesRepository>()
        val toggleRecipeFavorite = ToggleRecipeFavorite(repository)

        //When
        val recipeChanged = runBlocking { toggleRecipeFavorite.invoke(recipe) }

        //Then
        verifyBlocking(repository) { update(recipeChanged) }
    }

}