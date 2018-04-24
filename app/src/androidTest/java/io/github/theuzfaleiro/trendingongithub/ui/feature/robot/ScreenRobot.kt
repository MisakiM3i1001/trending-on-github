package io.github.theuzfaleiro.trendingongithub.ui.feature.robot

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*


abstract class ScreenRobot<T : ScreenRobot<T>> {

    fun checkIsHidden(@IdRes vararg viewIds: Int): T {
        viewIds.forEach {
            onView(withId(it)).check(matches(isDisplayed()))
        }

        return this as T
    }

    fun checkViewHasText(@IdRes viewId: Int, @StringRes stringId: Int): T {
        onView(withId(viewId))
                .check(matches(withText(stringId)))

        return this as T
    }

    fun checkIfTextDisplayed(textToBeMatched: String): T {
        onView(withText(textToBeMatched))
                .check(matches(isDisplayed()))

        return this as T
    }
}