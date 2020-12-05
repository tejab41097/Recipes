package com.tejas.recipe.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tejas.recipe.R
import com.tejas.recipe.databinding.FragmentCartBinding
import com.tejas.recipe.model.Recipe
import com.tejas.recipe.ui.adapter.CartAdapter
import com.tejas.recipe.ui.adapter.RecipesAdapter

class CartFragment : Fragment() {

    private var binding: FragmentCartBinding? = null
    private val fragmentBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartAdapter = CartAdapter()
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            fragmentBinding.rvCart.layoutManager = GridLayoutManager(requireActivity(), 2)
        else
            fragmentBinding.rvCart.layoutManager = GridLayoutManager(requireActivity(), 4)
        fragmentBinding.rvCart.adapter = cartAdapter
        val mainViewModel = (requireActivity() as MainActivity).mainViewModel
        mainViewModel.cartLiveData.observe(viewLifecycleOwner, {
            cartAdapter.updateCart(it)
        })

        cartAdapter.itemClickListener = object : CartAdapter.ItemClickListener{
            override fun onItemClicked(recipe: Recipe) {
                RecipeDetailFragment.setRecipes(recipe)
                (requireActivity() as MainActivity).navController.navigate(R.id.recipeDetailFragment)
            }
        }
    }

}