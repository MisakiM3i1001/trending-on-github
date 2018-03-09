package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresent: RepositoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        repositoryPresent.getRepositoriesFromApi("android", "stars", 1)

    }

    override fun displayRepositories(repositoryResponseList: RepositoryList) {
        Toast.makeText(this, repositoryResponseList.repositoryList.first().name, Toast.LENGTH_LONG).show()
    }
}
