package com.yape.recipe.domain.model

import com.yape.recipe.data.database.entities.RecipeEntity

data class Recipe(
    val id: Int,
    val title: String = "",
    val photo: String = "",
    val description: String = "",
    val duration: Int = 0,
    val location: Location = Location()
)

fun RecipeEntity.toDomain() = Recipe(id, title, photo, description, duration, location)
