package com.corcida.recipe.framework

import com.corcida.domain.Location
import com.corcida.domain.Recipe
import com.corcida.recipe.framework.local.entity.RecipeEntity
import com.corcida.recipe.framework.remote.model.Recipe as ServerRecipe

fun Recipe.toRoomRecipe(): RecipeEntity =
    RecipeEntity(
        id,
        title,
        overview,
        image,
        ingredients,
        tags,
        location,
        favorite
    )

fun RecipeEntity.toDomainRecipe(): Recipe =
    Recipe(
        id,
        title,
        overview,
        image,
        ingredients,
        tags,
        location,
        favorite
    )

fun ServerRecipe.toDomainRecipe(): Recipe =
    Recipe(
        id,
        title,
        overview,
        image,
        ingredients,
        tags,
        Location(location.title, location.latitude, location.longitude),
        false
    )

