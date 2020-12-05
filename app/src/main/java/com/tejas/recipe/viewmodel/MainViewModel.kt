package com.tejas.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tejas.recipe.model.Recipe
import com.tejas.recipe.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    private var disposable: CompositeDisposable = CompositeDisposable()
    private val recipesMutLiveData = MutableLiveData<MutableList<Recipe>>()
    private val errorMutLiveData = MutableLiveData<String>()
    private val loadingMutLiveData = MutableLiveData<Boolean>()
    private val cartMutListLiveData = MutableLiveData<MutableList<Recipe>>()

    val recipesLiveData: LiveData<MutableList<Recipe>> get() = recipesMutLiveData
    val errorLiveData: LiveData<String> get() = errorMutLiveData
    val loadingLiveData: LiveData<Boolean> get() = loadingMutLiveData
    val cartLiveData: LiveData<MutableList<Recipe>> get() = cartMutListLiveData

    fun updateCartLiveData(list: MutableList<Recipe>) {
        cartMutListLiveData.postValue(list)
    }

    fun getAllRecipes() {
        loadingMutLiveData.postValue(true)
        disposable.add(
            mainRepository.getAllRecipes().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribeWith(object : DisposableSingleObserver<MutableList<Recipe>>() {
                    override fun onError(e: Throwable) {
                        errorMutLiveData.postValue(
                            when (e) {
                                is UnknownHostException -> "Please, Check Internet Connection"
                                else -> e.message
                            }
                        )
                        loadingMutLiveData.postValue(false)
                    }

                    override fun onSuccess(recipes: MutableList<Recipe>) {
                        recipesMutLiveData.postValue(recipes)
                        loadingMutLiveData.postValue(false)
                    }
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposable.dispose()
    }
}