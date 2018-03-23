package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail

import android.os.Bundle
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.presenter.PullRequestDetailContract
import kotlinx.android.synthetic.main.activity_pull_request_detail.*
import javax.inject.Inject

class PullRequestDetailActivity : BaseActivity(), PullRequestDetailContract.View {

    @Inject
    lateinit var pullRequestDetailPresenter: PullRequestDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_detail)

        pullRequestDetailPresenter.hasPullRequestUrl(intent.hasExtra(PULL_REQUEST_SELECTED))
    }

    override fun displayPullRequestDetailOnWebView() {
        webViewPullRequestDetail.loadUrl(intent.extras.getParcelable<PullRequest>(PULL_REQUEST_SELECTED).htmlUrl)
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperPullRequestDetail.displayedChild = viewFlipperPosition
    }


    companion object {
        const val PULL_REQUEST_SELECTED: String = "PULL_REQUEST_SELECTED"
    }
}
