package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList

interface RepositoryContract {

    interface View {
        fun displayRepositories(repositoryResponseList: RepositoryList)
    }

    interface Presenter {
        fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int)
    }
}