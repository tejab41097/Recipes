package com.tejas.recipe.model

data class Recipe(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val label: String,
    val name: String,
    val price: String
)