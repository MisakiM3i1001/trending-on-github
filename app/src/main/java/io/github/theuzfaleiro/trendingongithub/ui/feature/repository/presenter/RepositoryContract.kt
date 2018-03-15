package io.github.theuzfaleiro.trendingongithub.ui.feature.repository.presenter

import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository

interface RepositoryContract {

    interface View {
        fun displayRepositories(repositoryResponseList: List<Repository>)

        fun changeViewFlipperPosition(viewFlipperPosition: Int)
    }

    interface Presenter {
        fun getRepositoriesFromApi(repositoryLanguage: String, sortOrder: String, page: Int = 1)
    }
}