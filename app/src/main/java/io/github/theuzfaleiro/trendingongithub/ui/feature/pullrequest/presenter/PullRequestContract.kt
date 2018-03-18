package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter

import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest

interface PullRequestContract {

    interface View {
        fun showPullRequestsInformation(pullRequestList: List<PullRequest>)

        fun changeViewFlipperPosition(viewFlipperPosition: Int)
        fun getPullRequestInformation()
    }

    interface Presenter {

        fun initPresenter(hasRepositorySelected: Boolean)

        fun getDataFromApi(repositoryOwner: String, repositoryName: String)
    }
}