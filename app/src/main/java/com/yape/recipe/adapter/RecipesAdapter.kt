package com.yape.recipe.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yape.recipe.R
import com.yape.recipe.domain.model.Recipe
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class RecipesAdapter (private val onClickListener : (Recipe, View) -> Unit) : RecyclerView.Adapter<RecipesViewHolder>() {

    var recipes: List<Recipe> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipesViewHolder(layoutInflater.inflate(R.layout.node_recipe, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = recipes[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = recipes.size

}