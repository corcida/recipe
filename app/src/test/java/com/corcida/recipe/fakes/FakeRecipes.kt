package com.corcida.recipe.fakes

import com.corcida.domain.Location
import com.corcida.domain.Recipe

object FakeRecipes {
    val fakeRecipes = listOf(
        Recipe(
        1,
            "test 1",
        "",
        "",
        listOf("ingredient 1", "ingredient 11", "expected ingredient"),
        listOf("tag 1", "tag 11", "expected tag"),
        Location("", 0.0, 0.0),
        false ),
        Recipe(
            2,
            "test 2",
            "",
            "",
            listOf("ingredient 2", "ingredient 22"),
            listOf("tag 2", "tag 22"),
            Location("", 0.0, 0.0),
            false ),

    )

    val expectedRecipe =  Recipe(
        1,
        "test 1",
        "",
        "",
        listOf("ingredient 1", "ingredient 11", "expected ingredient"),
        listOf("tag 1", "tag 11", "expected tag"),
        Location("", 0.0, 0.0),
        false )
}