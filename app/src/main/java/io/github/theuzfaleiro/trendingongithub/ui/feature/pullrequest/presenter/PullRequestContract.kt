package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest

interface PullRequestContract {

    interface View {
        fun showPullRequestsInformation(pullRequestList: List<PullRequest>)
    }

    interface Presenter {
        fun getDataFromApi()
    }
}