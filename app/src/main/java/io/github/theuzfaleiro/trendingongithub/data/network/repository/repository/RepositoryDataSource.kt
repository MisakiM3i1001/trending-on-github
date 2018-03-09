package io.github.theuzfaleiro.trendingongithub.data.network.repository.repository

import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.reactivex.Single


interface RepositoryDataSource {

    fun getRepositories(repositoryLanguage : String, sortOrder : String, page : Int) : Single<RepositoryList>
}