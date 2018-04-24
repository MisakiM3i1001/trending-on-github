package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import io.github.theuzfaleiro.trendingongithub.ui.feature.robot.ScreenRobot


class PullRequestRobot : ScreenRobot<PullRequestRobot>() {

    fun launchActivityWithoutParameters(activityTestRule: ActivityTestRule<*>): PullRequestRobot {
        activityTestRule.launchActivity(Intent())

        return this
    }

    fun isTextDisplayed(textToBeMatched: String): PullRequestRobot {
        checkIfTextDisplayed(textToBeMatched)

        return this
    }
}