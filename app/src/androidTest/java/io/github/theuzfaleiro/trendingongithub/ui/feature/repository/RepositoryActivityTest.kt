package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryActivityTest {

    @get:Rule
    private val repositoryActivityTestRule = IntentsTestRule(RepositoryActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun shouldDisplayAndroidRepositories_WhenFetchedDataFromAPI() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnFile(200, "repository/repository.json")

        repositoryActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.recyclerViewRepositories)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withText("kotlin")).check(matches(isDisplayed()))
        onView(withText("The Kotlin Programming Language")).check(matches(isDisplayed()))
        onView(withText("21293")).check(matches(isDisplayed()))
        onView(withText("2336")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayErrorMessage_WhenFailingFetchingDataFromAPI() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnEmpty(404)

        repositoryActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.imageViewLoadingError)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldOpenPullRequestActivity_WhenARepositoryWasSelected() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnFile(200, "repository/repository.json")

        repositoryActivityTestRule.launchActivity(Intent())

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        intending(hasComponent(PullRequestActivity::class.java.name)).respondWith(activityResult)

        onView(withId(R.id.recyclerViewRepositories)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        intended(hasComponent(PullRequestActivity::class.java.name))
    }
}