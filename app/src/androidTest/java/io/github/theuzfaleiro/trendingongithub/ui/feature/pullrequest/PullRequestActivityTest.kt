package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PullRequestDetailActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestActivityTest {

    @get:Rule
    val pullRequestActivityTestRule = IntentsTestRule(PullRequestActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldShowPullRequestDetails_WhenFetchedDataFromAPI() {
        RESTMockServer.whenGET(pathContains("repos/")).thenReturnFile(200, "pullrequest/pullrequest.json")

        pullRequestActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.recyclerViewPullRequest)).perform(RecyclerViewActions.scrollToPosition
        <RecyclerView.ViewHolder>(2))

        onView(withText("Minimal Weather GPS Location Fix")).check(matches(isDisplayed()))
        onView(withText("This should fix #1023 ")).check(matches(isDisplayed()))
        onView(withText("theuzfaleiro")).check(matches(isDisplayed()))

    }

    @Test
    fun shouldDisplayErrorMessage_WhenFailingFetchingDataFromAPI() {
        RESTMockServer.whenGET(pathContains("repos/")).thenReturnEmpty(404)

        pullRequestActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.imageViewRepositoryLoadingError)).check(matches(isDisplayed()))
    }


    @Test
    fun shouldOpenPullRequestActivity_WhenARepositoryWasSelected() {

        RESTMockServer.whenGET(pathContains("repos/")).thenReturnFile(200, "pullrequest/pullrequest.json")

        pullRequestActivityTestRule.launchActivity(Intent())

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        Intents.intending(IntentMatchers.hasComponent(PullRequestDetailActivity::class.java.name)).respondWith(activityResult)

        onView(withId(R.id.recyclerViewPullRequest)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))

        Intents.intended(IntentMatchers.hasComponent(PullRequestDetailActivity::class.java.name))
    }

}