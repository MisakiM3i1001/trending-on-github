package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import io.github.theuzfaleiro.trendingongithub.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestActivityTest {

    @get:Rule
    private val pullRequestActivityTestRule = ActivityTestRule(PullRequestActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldShowPullRequestDetails_WhenFetchedDataFromAPI() {
        RESTMockServer.whenGET(pathContains("repos/")).thenReturnFile(200, "pullrequest/pullrequest.json")

        pullRequestActivityTestRule.launchActivity(Intent())

        onView(withId(R.id.recyclerViewPullRequest)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))

        onView(withText("Frontend dependencies now use HTTPS. Fixes #1023")).check(matches(isDisplayed()))
        //onView(withText("This should fix #1023")).check(matches(isDisplayed()))
        onView(withText("TheWildHorse")).check(matches(isDisplayed()))

    }

}