package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.InfiniteScrollListener
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.adapter.BaseAdapter
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import kotlinx.android.synthetic.main.activity_repository.*
import javax.inject.Inject

class RepositoryActivity : BaseActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresenter: RepositoryContract.Presenter

    lateinit var baseAdapter: BaseAdapter

    var apiPagination = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        initAdapter()

        initRepositoryRecyclerView()

        repositoryPresenter.getRepositoriesFromApi("java", "stars")

    }

    private fun initAdapter() {
        baseAdapter = BaseAdapter { repositorySelected ->
            startActivity(Intent(this@RepositoryActivity,
                    PullRequestActivity::class.java).putExtra("REPOSITORY_SELECTED", repositorySelected))
        }
    }

    private fun initRepositoryRecyclerView() {
        with(recyclerViewRepositories) {
            setHasFixedSize(true)

            val a = LinearLayoutManager(this@RepositoryActivity)

            layoutManager = a
            adapter = baseAdapter
            addOnScrollListener(InfiniteScrollListener({
                repositoryPresenter.getRepositoriesFromApi("java", "stars", apiPagination++)
            }, layoutManager = a))

        }
    }

    override fun displayRepositories(repositoryResponseList: List<Repository>) {
        baseAdapter.addNews(repositoryResponseList)
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperRepository.displayedChild = viewFlipperPosition
    }
}