package com.corcida.recipe.ui.screens.map

import com.corcida.recipe.fakes.FakeRecipes
import com.corcida.recipe.rules.MainDispatcherRule
import com.corcida.usecases.FindRecipeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MapViewModelTest {

    //system in test
    private lateinit var viewModel: MapViewModel

    //dependencies
    private lateinit var findRecipeUseCase: FindRecipeUseCase

    //rules
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @Before
    fun setUp() {
        val expectedId = 1
        findRecipeUseCase = mock(){
            onBlocking { invoke(expectedId) } doReturn (FakeRecipes.fakeRecipes.first { it.id == 1 })
        }
        viewModel = MapViewModel(findRecipeUseCase)
    }

    @Test
    fun `When the user enters to the screen then the view model get the expected location`() = runTest {
        //Given
        val expectedLocation =  FakeRecipes.expectedLocation

        //When
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.location.collect()
        }
        launch { viewModel.findLocationFromRecipe(1) }
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.location.value == expectedLocation) }
    }
}