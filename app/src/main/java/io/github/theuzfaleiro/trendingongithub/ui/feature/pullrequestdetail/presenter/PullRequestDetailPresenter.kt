package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.presenter

class PullRequestDetailPresenter(private val pullRequestView: PullRequestDetailContract.View) : PullRequestDetailContract.Presenter {

    override fun hasPullRequestUrl(hasRepositorySelected: Boolean) {
        if (hasRepositorySelected) {
            pullRequestView.displayPullRequestDetailOnWebView()
        } else {
            pullRequestView.changeViewFlipperPosition(1)
        }
    }
}