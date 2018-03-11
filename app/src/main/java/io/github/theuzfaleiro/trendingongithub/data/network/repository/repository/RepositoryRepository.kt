package io.github.theuzfaleiro.trendingongithub.data.network.repository.repository

import io.github.theuzfaleiro.trendingongithub.data.network.GitHubEndPoint
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.reactivex.Single

open class RepositoryRepository(private val gitHubEndPoint: GitHubEndPoint) {

    open fun getRepositories(repositoryLanguage: String, sortOrder: String, page: Int = 1): Single<RepositoryList> =
            gitHubEndPoint.getRepositoriesFromGitHub(repositoryLanguage, sortOrder, page)

}