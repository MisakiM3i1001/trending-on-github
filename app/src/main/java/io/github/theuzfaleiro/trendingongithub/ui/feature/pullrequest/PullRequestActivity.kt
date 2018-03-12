package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.presenter.PullRequestContract
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


    override fun showPullRequestsInformation(pullRequestList: List<PullRequest>) {
        recyclerViewPullRequest.adapter = PullRequestAdapter(pullRequestList, { pullRequest ->
            Toast.makeText(this@PullRequestActivity, pullRequest.title, Toast.LENGTH_LONG).show()
        })
    }

    private fun initPullRequestRecyclerView() {
        with(recyclerViewPullRequest) {
            layoutManager = LinearLayoutManager(this@PullRequestActivity,
                    LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
}
