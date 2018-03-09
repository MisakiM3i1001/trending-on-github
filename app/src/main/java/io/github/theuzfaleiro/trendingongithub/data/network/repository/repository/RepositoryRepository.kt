package io.github.theuzfaleiro.trendingongithub.data.network.repository.repository

import io.github.theuzfaleiro.trendingongithub.data.network.GitHubEndPoint
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.reactivex.Single

class RepositoryRepository(private val gitHubEndPoint: GitHubEndPoint) {

    fun getRepositories(repositoryLanguage: String, sortOrder: String, page: Int): Single<RepositoryList> =
            gitHubEndPoint.getRepositoriesFromGitHub(repositoryLanguage, sortOrder, page)

}