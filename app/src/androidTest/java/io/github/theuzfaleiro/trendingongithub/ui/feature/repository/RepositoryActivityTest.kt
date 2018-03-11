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
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
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
    private val repositoryActivityActivityTestRule = IntentsTestRule(RepositoryActivity::class.java, true, false)


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        RESTMockServer.reset()

        Intents.release()
    }

    @Test
    fun shouldDisplayAndroidRepositories_WhenFetchDataFromAPI() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnFile(200, "repository/repository.json")

        repositoryActivityActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.recyclerViewRepositories)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }

    @Test
    fun shouldDisplayErrorMessage_WhenFailingFetchingDataFromAPI() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnEmpty(404)

        repositoryActivityActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.imageViewLoadingError)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShownProductInstallments_WhenSeeMoreInstallmentsWasClicked() {

        RESTMockServer.whenGET(pathContains("search/repositories")).thenReturnFile(200, "repository/repository.json")

        repositoryActivityActivityTestRule.launchActivity(Intent())

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        intending(hasComponent(PullRequestActivity::class.java.name)).respondWith(activityResult)

        onView(withId(R.id.recyclerViewRepositories)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        intended(hasComponent(PullRequestActivity::class.java.name))
    }
}