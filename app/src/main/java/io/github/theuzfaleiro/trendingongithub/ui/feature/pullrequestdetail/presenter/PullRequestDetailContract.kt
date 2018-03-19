package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.presenter

interface PullRequestDetailContract {

    interface View {
        fun displayPullRequestDetailOnWebView()

        fun changeViewFlipperPosition(viewFlipperPosition: Int)
    }

    interface Presenter {
        fun hasPullRequestUrl(hasRepositorySelected: Boolean)
    }
}