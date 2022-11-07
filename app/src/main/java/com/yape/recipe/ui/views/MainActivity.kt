package com.yape.recipe.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yape.recipe.R
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}