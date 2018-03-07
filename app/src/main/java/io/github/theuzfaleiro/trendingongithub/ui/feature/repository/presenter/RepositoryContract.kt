package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository

interface RepositoryContract {

    interface View {
        fun displayRepositories(repositoryResponseList: List<Repository>)
    }

    interface Presenter {
        fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int)
    }
}