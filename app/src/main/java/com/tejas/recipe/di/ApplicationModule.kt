package com.tejas.recipe.di

import com.tejas.recipe.network.ApiClient
import com.tejas.recipe.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun getApiClient(): ApiClient {
        return ApiClient()
    }
}