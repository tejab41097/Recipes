package com.tejas.recipe.repository

import com.tejas.recipe.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getAllRecipes() = apiService.getRecipes()
}