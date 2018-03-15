package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter.PullRequestContract
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PullRequestDetailActivity
import kotlinx.android.synthetic.main.activity_pull_request.*
import javax.inject.Inject

class PullRequestActivity : BaseActivity(), PullRequestContract.View {

    @Inject
    lateinit var pullRequestPresenter: PullRequestContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pull_request)

        initPullRequestRecyclerView()

        pullRequestPresenter.getDataFromApi()
    }


    override fun showPullRequestsInformation(pullRequestList: List<io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest>) {
        recyclerViewPullRequest.adapter = PullRequestAdapter(pullRequestList, { pullRequest ->
            startActivity(Intent(this@PullRequestActivity, PullRequestDetailActivity::class.java))
        })
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperPullRequest.displayedChild = viewFlipperPosition
    }


    private fun initPullRequestRecyclerView() {
        with(recyclerViewPullRequest) {
            layoutManager = LinearLayoutManager(this@PullRequestActivity,
                    LinearLayoutManager.VERTICAL, false)
            adapter = PullRequestAdapter {}

            setHasFixedSize(true)
        }
    }
}
