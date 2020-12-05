package com.tejas.recipe.di

import com.tejas.recipe.network.ApiClient
import com.tejas.recipe.network.ApiService
import dagger.Module
import dagger.Provides

@Module(subcomponents = [MainActivityComponent::class])
class MainActivityModule {

    @Provides
    fun getApiService(apiClient: ApiClient): ApiService {
        return apiClient.getRetrofitInstance().create(ApiService::class.java)
    }
}