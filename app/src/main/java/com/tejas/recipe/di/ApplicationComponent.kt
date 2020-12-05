package com.tejas.recipe.di

import android.content.Context
import com.tejas.recipe.util.ViewModelFactoryModule
import com.tejas.recipe.util.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, MainActivityModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun mainActivityComponent(): MainActivityComponent.MainActivityFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}