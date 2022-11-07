package com.yape.recipe.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yape.recipe.databinding.NodeRecipeBinding
import com.yape.recipe.domain.model.Recipe
import com.yape.recipe.utils.Constants

class RecipesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = NodeRecipeBinding.bind(view)

    fun render(recipe: Recipe, onClickListener : (Recipe, View) -> Unit) {
        if  (recipe.id != Constants.loading){
            Glide.with(binding.photo.context).load(recipe.photo).into(binding.photo)
            binding.shimmerViewContainer.stopShimmer()
            binding.photo.visibility = View.VISIBLE
            binding.title.text = recipe.title
            val durationString = "${recipe.duration} min"
            binding.duration.text = durationString
            binding.shimmerViewContainer.visibility = View.GONE
        }else {
            binding.shimmerViewContainer.startShimmer()
        }
        itemView.setOnClickListener { onClickListener(recipe, binding.photo) }
    }

}