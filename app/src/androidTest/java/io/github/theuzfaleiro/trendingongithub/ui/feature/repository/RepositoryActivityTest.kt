package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import io.github.theuzfaleiro.trendingongithub.R
import org.junit.Rule
import org.junit.Test


class RepositoryActivityTest {

    @Rule
    val activityTestRule = ActivityTestRule<RepositoryActivity>(RepositoryActivity::class.java, true, false)


    //val mockWebServer: MockWebServer = MockWebServer()


    @Test
    private fun shouldDisplayAndroidRepositories_WhenFetchDataFromAPI() {
        RESTMockServer.whenGET(pathContains("forecast/daily")).thenReturnFile(200, "repository/repository.json")

        activityTestRule.launchActivity(Intent())


        onView(withId(R.id.imageViewForkCount)).check(matches(isDisplayed()))
    }
}