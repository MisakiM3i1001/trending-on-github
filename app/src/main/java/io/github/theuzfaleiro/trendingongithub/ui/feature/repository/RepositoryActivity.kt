package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter.RepositoryContract
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View {

    @Inject
    lateinit var repositoryPresent: RepositoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

    }

    override fun displayRepositories(repositoryResponseList: List<Repository>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
