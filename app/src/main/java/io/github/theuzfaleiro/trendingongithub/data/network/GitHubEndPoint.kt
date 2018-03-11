package io.github.theuzfaleiro.trendingongithub.data.network

import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.RepositoryList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubEndPoint {

    @GET("search/repositories")
    fun getRepositoriesFromGitHub(@Query("q") repositoryLanguage: String, @Query("sort") sortOrder: String, @Query("page") page: Int = 1): Single<RepositoryList>

    @GET("repos/{repositoryOwner}/{repositoryName}/pulls")
    fun getPullRequestsFromRepository(@Path("owner") repositoryOwner: String, @Path("name") repositoryName: String): Single<RepositoryList>
}