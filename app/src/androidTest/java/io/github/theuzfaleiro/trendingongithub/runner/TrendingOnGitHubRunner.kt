package io.github.theuzfaleiro.trendingongithub.runner

import android.app.Application
import android.content.Context
import io.appflate.restmock.android.RESTMockTestRunner
import io.github.theuzfaleiro.trendingongithub.TrendingOnGitHubTestApplication

class TrendingOnGitHubRunner : RESTMockTestRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TrendingOnGitHubTestApplication::class.java.name, context)
    }
}