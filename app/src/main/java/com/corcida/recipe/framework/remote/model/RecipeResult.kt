package com.corcida.recipe.framework.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RecipeResult(
    val results: List<Recipe>
)

@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val location: Location
): Parcelable

@Parcelize
data class Location(
    val title: String,
    val latitude: Double,
    val longitude: Double
): Parcelable

