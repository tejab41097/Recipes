package com.tejas.recipe

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.tejas.recipe.ui.MainActivity
import com.tejas.recipe.ui.adapter.RecipesAdapter
import org.junit.Rule
import org.junit.Test


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testOpenRecipe() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_recipes)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecipesAdapter.RecipeViewHolder>(
                0,
                click()
            )
        )
    }
}