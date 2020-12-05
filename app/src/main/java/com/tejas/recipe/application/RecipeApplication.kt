package com.tejas.recipe.application

import android.app.Application
import com.tejas.recipe.di.ApplicationComponent
import com.tejas.recipe.di.DaggerApplicationComponent

class RecipeApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.factory().create(applicationContext)
        super.onCreate()
    }
}