package com.yape.recipe.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yape.recipe.R
import com.yape.recipe.adapter.RecipesAdapter
import com.yape.recipe.databinding.ActivityMainBinding
import com.yape.recipe.domain.model.Recipe
import com.yape.recipe.ui.vm.MainViewModel
import com.yape.recipe.ui.vm.MainViewModel.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : RecipesAdapter
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setViews()
        viewModel.model.observe(this, Observer(this::observeViewModel))
        viewModel.onCreate()
    }

    private fun setViews(){
        binding.filter.addTextChangedListener { viewModel.filterRecipe(it.toString()) }
        adapter = RecipesAdapter{ recipe: Recipe, view: View ->
            viewModel.onMovieItemSelected(recipe, view) }
        binding.list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.list.adapter = adapter
    }

    private fun observeViewModel(model: UiModel){
        when (model){
            is UiModel.Error -> {

            }
            is UiModel.RecipeData -> adapter.recipes = model.data

            is UiModel.RecipeSelected -> {

            }
        }
    }

}