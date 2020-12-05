package com.tejas.recipe.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tejas.recipe.R
import com.tejas.recipe.databinding.FragmentRecipesBinding
import com.tejas.recipe.model.Recipe
import com.tejas.recipe.ui.adapter.RecipesAdapter
import com.tejas.recipe.viewmodel.MainViewModel

class RecipesFragment : Fragment() {

    private var binding: FragmentRecipesBinding? = null
    private val fragmentBinding get() = binding!!
    private lateinit var mainViewModel: MainViewModel
    private val recipeAdapter = RecipesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (requireActivity() as MainActivity).mainViewModel
        mainViewModel.getAllRecipes()
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            fragmentBinding.rvRecipes.layoutManager = GridLayoutManager(requireActivity(), 2)
        else
            fragmentBinding.rvRecipes.layoutManager = GridLayoutManager(requireActivity(), 4)

        fragmentBinding.rvRecipes.adapter = recipeAdapter
        mainViewModel.errorLiveData.observe(viewLifecycleOwner, {
            (requireActivity() as MainActivity).showSnackBar(it)
        })

        mainViewModel.loadingLiveData.observe(viewLifecycleOwner, {
            fragmentBinding.ivLoading.isVisible = it
        })

        mainViewModel.recipesLiveData.observe(viewLifecycleOwner, { recipes ->
            recipeAdapter.updateRecipes(recipes)
        })

        mainViewModel.cartLiveData.observe(viewLifecycleOwner, { cartList ->
            recipeAdapter.updateCart(cartList)
        })

        fragmentBinding.btnShowCart.setOnClickListener {
            mainViewModel.updateCartLiveData(recipeAdapter.getCart())
            (requireActivity() as MainActivity).navController.navigate(R.id.cartFragment)
        }

        recipeAdapter.itemClickListener = object : RecipesAdapter.ItemClickListener {
            override fun onItemClicked(recipe: Recipe) {
                RecipeDetailFragment.setRecipes(recipe)
                (requireActivity() as MainActivity).navController.navigate(R.id.recipeDetailFragment)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.updateCartLiveData(recipeAdapter.getCart())
    }

}