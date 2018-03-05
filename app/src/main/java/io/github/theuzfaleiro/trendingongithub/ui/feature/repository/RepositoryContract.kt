package io.github.theuzfaleiro.trendingongithub.ui.feature.repository

import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BasePresenter
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.BaseView

interface RepositoryContract {

    interface RepositoryView : BaseView {
        fun displayRepositories(repositoryResponseList: List<Repository>)
    }

    interface RepositoryPresenter : BasePresenter {
        fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int)
    }
}