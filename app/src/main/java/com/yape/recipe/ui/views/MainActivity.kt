package com.yape.recipe.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.yape.recipe.R
import com.yape.recipe.databinding.ActivityMainBinding
import com.yape.recipe.ui.vm.MainViewModel
import com.yape.recipe.ui.vm.MainViewModel.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        viewModel.model.observe(this, Observer(this::observeViewModel))
        viewModel.onCreate()
    }

    private fun setViews(){
        binding.filter.addTextChangedListener { viewModel.filterRecipe(it.toString()) }
    }

    private fun observeViewModel(model: UiModel){
        when (model){
            is UiModel.Error -> {

            }
            is UiModel.RecipeData -> {

            }
            is UiModel.RecipeSelected -> {

            }
        }
    }

}