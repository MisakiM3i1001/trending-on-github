package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.theuzfaleiro.trendingongithub.R
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryAdapter
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import kotlinx.android.synthetic.main.activity_repository.*
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresent: RepositoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        initRepositoryRecyclerView()

        repositoryPresent.getRepositoriesFromApi("android", "stars", 1)

    }

    private fun initRepositoryRecyclerView() {
        with(recyclerViewRepositories) {
            layoutManager = LinearLayoutManager(this@RepositoryActivity,
                    LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun displayRepositories(repositoryResponseList: RepositoryList) {
        recyclerViewRepositories.adapter = RepositoryAdapter(repositoryResponseList.repositoryList, { repositoryClick ->
            Toast.makeText(this, repositoryClick.name, Toast.LENGTH_LONG).show()
        })
    }
}