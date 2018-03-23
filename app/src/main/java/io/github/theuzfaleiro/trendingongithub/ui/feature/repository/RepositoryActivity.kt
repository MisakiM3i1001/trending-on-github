package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.InfiniteScrollListener
import io.github.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.adapter.GenericAdapter
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import kotlinx.android.synthetic.main.activity_repository.*
import javax.inject.Inject

class RepositoryActivity : BaseActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresenter: RepositoryContract.Presenter

    lateinit var genericAdapter: GenericAdapter

    private var apiPagination = 2

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        repositoryPresenter.initPresenter()

        initAdapter()

        initRepositoryRecyclerView()

        repositoryPresenter.getRepositoriesFromApi("java", "stars")

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putString("AAA", "BBB")
//            putParcelableArray("hadouken", List<Repository>().toTypedArray())
        }
        super.onSaveInstanceState(outState)
    }

    private fun initAdapter() {
        genericAdapter = GenericAdapter { repositorySelected ->
            startActivity(Intent(this@RepositoryActivity,
                    PullRequestActivity::class.java).putExtra(PullRequestActivity.REPOSITORY_SELECTED, repositorySelected))
        }
    }

    private fun initRepositoryRecyclerView() {
        with(recyclerViewRepositories) {
            setHasFixedSize(true)

            val a = LinearLayoutManager(this@RepositoryActivity)

            layoutManager = a
            adapter = genericAdapter
            addOnScrollListener(InfiniteScrollListener({
                repositoryPresenter.getRepositoriesFromApi("java", "stars", apiPagination++)
            }, layoutManager = a))

        }
    }

    override fun displayRepositories(repositoryResponseList: List<Repository>) {
        genericAdapter.addNews(repositoryResponseList)
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperRepository.displayedChild = viewFlipperPosition
    }
}