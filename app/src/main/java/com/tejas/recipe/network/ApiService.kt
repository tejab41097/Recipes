package com.tejas.recipe.network

import com.tejas.recipe.model.Recipe
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/he-public-data/reciped9d7b8c.json")
    fun getRecipes(): Single<MutableList<Recipe>>
}