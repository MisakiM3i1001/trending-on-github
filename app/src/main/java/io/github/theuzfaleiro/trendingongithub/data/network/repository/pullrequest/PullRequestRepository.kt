package io.github.theuzfaleiro.trendingongithub.data.network.repository.pullrequest

import io.github.theuzfaleiro.trendingongithub.data.network.GitHubEndPoint
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import io.reactivex.Single

open class PullRequestRepository(private val gitHubEndPoint: GitHubEndPoint) {

    open fun getPullRequestsFromSelectedRepository(ownerName: String, repositoryName: String): Single<List<PullRequest>> =
            gitHubEndPoint.getPullRequestsFromRepository(ownerName, repositoryName)

}