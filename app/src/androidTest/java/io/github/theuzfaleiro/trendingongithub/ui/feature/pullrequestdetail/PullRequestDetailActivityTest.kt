package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.User
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PullRequestDetailActivityTest {

    @get:Rule
    val pullRequestDetailActivityRule = ActivityTestRule(PullRequestDetailActivity::class.java, true, false)

    @Before
    fun setUp() {
        //RESTMockServer.reset()
    }

    @Test
    fun shouldShowPullRequestDetailWebView_WhenPullRequestHasUrl() {
        pullRequestDetailActivityRule.launchActivity(Intent().putExtra(PullRequestDetailActivity.PULL_REQUEST_SELECTED,
                PullRequest("trending-on-github.html", "What's Trending on GitHub Website",
                        User("theuzfaleiro", "avatar42.png"), "The #42 PR Solves Everything"
                        , "18/04/2018")))

        onView(withId(R.id.webViewPullRequestDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowErrorImage_WhenPullRequestHasNotAnUrl() {
        pullRequestDetailActivityRule.launchActivity(Intent())

        onView(withId(R.id.imageViewPullRequestDetailError)).check(matches(isDisplayed()))
    }

}