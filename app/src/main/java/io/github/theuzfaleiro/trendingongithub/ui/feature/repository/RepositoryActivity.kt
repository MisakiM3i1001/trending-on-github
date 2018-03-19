package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseActivity
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.InfiniteScrollListener
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.adapter.DelegateAdapter
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import kotlinx.android.synthetic.main.activity_repository.*
import javax.inject.Inject

class RepositoryActivity : BaseActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresenter: RepositoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        initRepositoryRecyclerView()

        repositoryPresenter.getRepositoriesFromApi("kotlin", "stars")

    }

    private fun initRepositoryRecyclerView() {
        with(recyclerViewRepositories) {
            setHasFixedSize(true)
            addOnScrollListener(InfiniteScrollListener({
                repositoryPresenter.getRepositoriesFromApi("kotlin", "stars", 1)
            }, layoutManager = LinearLayoutManager(this@RepositoryActivity,
                    LinearLayoutManager.VERTICAL, false)))
        }
    }

    override fun displayRepositories(repositoryResponseList: List<Repository>) {
        recyclerViewRepositories.adapter = DelegateAdapter()
    }

    override fun changeViewFlipperPosition(viewFlipperPosition: Int) {
        viewFlipperRepository.displayedChild = viewFlipperPosition
    }
}