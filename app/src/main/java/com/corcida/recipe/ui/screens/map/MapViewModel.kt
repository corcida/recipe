package com.corcida.recipe.ui.screens.map

import com.corcida.domain.Location
import com.corcida.recipe.ui.screens.common.ScopeViewModel
import com.corcida.usecases.FindRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel  @Inject constructor(
    private val getRecipe: FindRecipe
) : ScopeViewModel() {

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    init {
        initScope()
    }

    fun findLocationFromRecipe(id: Int){
        launch {
            _location.value = getRecipe.invoke(id).location
        }
    }

}