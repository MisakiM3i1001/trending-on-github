package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryActivityTest {

    @get:Rule
    private val repositoryActivityActivityTestRule: ActivityTestRule<RepositoryActivity> = ActivityTestRule(RepositoryActivity::class.java, true, false)


    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldDisplayAndroidRepositories_WhenFetchDataFromAPI() {
        RESTMockServer.whenGET(pathContains("forecast/daily")).thenReturnFile(200, "repository/repository.json")

        repositoryActivityActivityTestRule.launchActivity(Intent())

        onView(withText("Trending On GitHub")).check(matches(isDisplayed()))
    }

    @Test
    @Ignore
    fun shouldShownProductInstallments_WhenSeeMoreInstallmentsWasClicked() {
        repositoryActivityActivityTestRule.launchActivity(Intent().putExtra("productId", "1"))

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        intending(hasComponent(RepositoryActivity::class.java.name)).respondWith(activityResult)

        intended(hasComponent(RepositoryActivity::class.java.name))
    }
}