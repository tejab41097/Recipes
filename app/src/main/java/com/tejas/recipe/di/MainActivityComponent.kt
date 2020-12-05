package com.tejas.recipe.di

import com.tejas.recipe.ui.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainActivityComponent {

    @Subcomponent.Factory
    interface MainActivityFactory {
        fun create(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}