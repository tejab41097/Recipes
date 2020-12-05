package com.tejas.recipe.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tejas.recipe.R
import com.tejas.recipe.databinding.FragmentRecipeDetailBinding
import com.tejas.recipe.model.Recipe

class RecipeDetailFragment : Fragment() {
    private var binding: FragmentRecipeDetailBinding? = null
    private val fragmentBinding get() = binding!!

    companion object {
        lateinit var recipe: Recipe
        fun setRecipes(recipe: Recipe) {
            this.recipe = recipe
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingMainActivity.toolbarMain.title = recipe.name
        Glide.with(requireContext())
            .load(recipe.image)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_warning)
            .centerCrop()
            .into(fragmentBinding.ivRecipe)

        fragmentBinding.tvCategoryActual.text = recipe.category
        fragmentBinding.tvDescription.text = recipe.description
        fragmentBinding.tvPrice.text = recipe.price
        fragmentBinding.tvTitle.text = recipe.name
        if (recipe.label != "") {
            fragmentBinding.tvLabelActual.text = recipe.label
            fragmentBinding.tvLabelActual.isVisible = true
        }
    }
}