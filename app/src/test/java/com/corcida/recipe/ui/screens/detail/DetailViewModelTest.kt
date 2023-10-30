package com.corcida.recipe.ui.screens.detail

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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    //system in test
    private lateinit var viewModel: DetailViewModel

    //dependencies
    private lateinit var findRecipeUseCase: FindRecipeUseCase

    //rules
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp(){
        val expectedId = 1
        findRecipeUseCase = mock(){
            onBlocking { invoke(expectedId) } doReturn (FakeRecipes.fakeRecipes.first { it.id == 1 })
        }
        viewModel = DetailViewModel(findRecipeUseCase)
    }

    @Test
    fun `When the user enters to the screen then the view model get the expected recipe`() = runTest {
        //Given
        val expectedRecipe =  FakeRecipes.expectedRecipe

        //When
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.recipe.collect()
        }
        launch { viewModel.findRecipeById(1) }
        advanceUntilIdle()

        //Then
        runBlocking { assert(viewModel.recipe.value == expectedRecipe) }
    }
}