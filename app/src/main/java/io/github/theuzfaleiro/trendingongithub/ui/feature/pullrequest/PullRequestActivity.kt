package io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.pullrequest.PullRequest
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
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

        pullRequestPresenter.initPresenter(intent.hasExtra(REPOSITORY_SELECTED))

        initPullRequestRecyclerView()
    }


    override fun showPullRequestsInformation(pullRequestList: List<PullRequest>) {
        recyclerViewPullRequest.adapter = PullRequestAdapter(pullRequestList, { pullRequestSelected ->
            startActivity(Intent(this@PullRequestActivity,
                    PullRequestDetailActivity::class.java).putExtra(PullRequestDetailActivity.PULL_REQUEST_SELECTED, pullRequestSelected))
        })
    }

    override fun getPullRequestInformation() {
        val selectedRepository: Repository = intent.getParcelableExtra(REPOSITORY_SELECTED)

        pullRequestPresenter.getDataFromApi(selectedRepository.owner.userName, selectedRepository.name)
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperPullRequest.displayedChild = viewFlipperPosition
    }


    private fun initPullRequestRecyclerView() {
        with(recyclerViewPullRequest) {
            layoutManager = LinearLayoutManager(this@PullRequestActivity,
                    LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@PullRequestActivity, DividerItemDecoration.VERTICAL))
            adapter = PullRequestAdapter {}

            setHasFixedSize(true)
        }
    }


    companion object {
        const val REPOSITORY_SELECTED: String = "REPOSITORY_SELECTED"
    }
}
