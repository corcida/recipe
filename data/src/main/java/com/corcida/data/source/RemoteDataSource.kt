package com.corcida.data.source

import com.corcida.domain.Recipe

interface RemoteDataSource {
    suspend fun getRecipes(): List<Recipe>
}