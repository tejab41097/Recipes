package com.tejas.recipe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tejas.recipe.R
import com.tejas.recipe.databinding.ItemRecipeBinding
import com.tejas.recipe.model.Recipe

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {
    private var recipeList = mutableListOf<Recipe>()

        lateinit var itemClickListener: ItemClickListener
    private var cartList = mutableListOf<Recipe>()

    interface ItemClickListener {
        fun onItemClicked(recipe: Recipe)
    }

    fun getCart() = cartList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeViewHolder(
        ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        val itemBinding = holder.itemBinding
        holder.setIsRecyclable(false)
        Glide.with(itemBinding.root.context)
            .load(recipe.image)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_warning)
            .centerCrop()
            .into(itemBinding.ivRecipe)

        itemBinding.tvCategoryActual.text = recipe.category
        itemBinding.tvDescription.text = recipe.description
        itemBinding.tvPrice.text = recipe.price
        itemBinding.tvTitle.text = recipe.name
        if (recipe.label != "") {
            itemBinding.tvLabelActual.text = recipe.label
            itemBinding.tvLabelActual.isVisible = true
        }
        itemBinding.root.setOnClickListener {
            itemClickListener.onItemClicked(recipe)
        }

        if (cartList.contains(recipe)) {
            itemBinding.btnCart.setImageResource(R.drawable.ic_remove_from_cart)
        } else
            itemBinding.btnCart.setImageResource(R.drawable.ic_add_to_cart)

        itemBinding.btnCart.setOnClickListener {
            if (cartList.contains(recipe))
                cartList.remove(recipe)
            else
                cartList.add(recipe)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = recipeList.size

    inner class RecipeViewHolder(val itemBinding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    fun updateRecipes(listOfRecipe: MutableList<Recipe>) {
        recipeList = listOfRecipe
        notifyDataSetChanged()
    }

    fun updateCart(cartList: MutableList<Recipe>) {
        this.cartList = cartList
        notifyDataSetChanged()
    }
}