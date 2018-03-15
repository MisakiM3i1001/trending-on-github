package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import kotlinx.android.synthetic.main.activity_repository.*
import javax.inject.Inject

class RepositoryActivity : BaseActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresent: RepositoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        initRepositoryRecyclerView()

        repositoryPresent.getRepositoriesFromApi("kotlin", "stars")

    }

    private fun initRepositoryRecyclerView() {
        with(recyclerViewRepositories) {
            layoutManager = LinearLayoutManager(this@RepositoryActivity,
                    LinearLayoutManager.VERTICAL, false)
            adapter = RepositoryAdapter {}
            setHasFixedSize(true)
        }
    }

    override fun displayRepositories(repositoryResponseList: List<Repository>) {
        recyclerViewRepositories.adapter = RepositoryAdapter(repositoryResponseList, { repositoryClick ->
            startActivity(Intent(this@RepositoryActivity, PullRequestActivity::class.java))
        })
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperRepository.displayedChild = viewFlipperPosition
    }
}