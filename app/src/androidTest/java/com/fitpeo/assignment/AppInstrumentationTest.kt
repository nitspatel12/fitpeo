package com.fitpeo.assignment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fitpeo.assignment.view.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppInstrumentationTest {

    @Test
    fun testHomeFragmentIsDisplayed() {
        // Launch the activity containing the Home fragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Find the Home fragment's view and verify that it is displayed
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickingRecyclerViewItemOpensDetailFragment() {
        // Launch the activity containing the Home fragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Find the first item in the RecyclerView and click on it
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Verify that the Detail fragment's view is displayed
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailFragmentDisplaysCorrectData() {
        // Launch the activity containing the Home fragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Find the first item in the RecyclerView and click on it
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Verify that the Detail fragment's view is displayed
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))

        // Verify that the Detail fragment displays the correct data
        onView(withId(R.id.tvTitle)).check(matches(withText("accusamus beatae ad facilis cum similique qui sunt")))
    }
}