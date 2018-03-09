package io.github.theuzfaleiro.trendingongithub

import io.appflate.restmock.RESTMockServer

class TrendingOnGitHubTestApplication : TrendingOnGitHubApplication() {

    override fun getBaseUrl(): String {
        return RESTMockServer.getUrl()
    }
}