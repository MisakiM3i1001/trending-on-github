package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryActivityTest {

    @get:Rule
    val repositoryActivityTestRule = IntentsTestRule(RepositoryActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldDisplayAndroidRepositories_WhenFetchedDataFromAPI() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnFile(200, "repository/repository.json")

        repositoryActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.recyclerViewRepositories)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withText("shadowsocks-android")).check(matches(isDisplayed()))
        onView(withText("A shadowsocks client for Android")).check(matches(isDisplayed()))
        onView(withText("12989")).check(matches(isDisplayed()))
        onView(withText("6307")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayErrorMessage_WhenFailingFetchingDataFromAPI() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnEmpty(404)

        repositoryActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.imageViewRepositoryLoadingError)).check(matches(isDisplayed()))
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