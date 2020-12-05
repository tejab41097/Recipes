package com.tejas.recipe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.tejas.recipe.R
import com.tejas.recipe.application.RecipeApplication
import com.tejas.recipe.databinding.ActivityMainBinding
import com.tejas.recipe.di.MainActivityComponent
import com.tejas.recipe.util.ViewModelFactory
import com.tejas.recipe.viewmodel.MainViewModel
import javax.inject.Inject


private var binding: ActivityMainBinding? = null
val bindingMainActivity get() = binding!!

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    lateinit var mainViewModel: MainViewModel

    lateinit var navController: NavController
    private lateinit var mainActivityComponent: MainActivityComponent
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as RecipeApplication).onCreate()
        mainActivityComponent =
            (applicationContext as RecipeApplication).applicationComponent.mainActivityComponent()
                .create()
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainActivity.root)
        appBarConfig = AppBarConfiguration.Builder(
            setOf(
                R.id.recipesFragment
            )
        ).build()
        navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(bindingMainActivity.toolbarMain)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfig)

    fun showSnackBar(message: String = "Something Went Wrong", isError: Boolean = true) {
        val snackBar = Snackbar.make(
            bindingMainActivity.root, HtmlCompat.fromHtml(
                "<b><u>$message</u></b>",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            ),
            Snackbar.LENGTH_LONG
        )

        snackBar.apply {
            if (isError) {
                setTextColor(
                    ContextCompat.getColor(this@MainActivity, R.color.design_default_color_error)
                )
                setBackgroundTint(
                    ContextCompat.getColor(applicationContext, R.color.white)
                )
            } else {
                setTextColor(
                    ContextCompat.getColor(applicationContext, R.color.blue_transparent)
                )
                setBackgroundTint(
                    ContextCompat.getColor(applicationContext, R.color.white)
                )
            }
            show()
        }
    }
}