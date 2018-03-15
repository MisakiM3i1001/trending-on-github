package io.github.theuzfaleiro.trendingongithub.data.model.pullrequest

import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest

data class PullRequest(val htmlUrl: String,
                       val title: String,
                       val user: User,
                       val body: String,
                       val createdAt: String) {
    constructor(pullRequest: PullRequest) : this(
            htmlUrl = pullRequest.htmlUrl,
            title = pullRequest.title,
            user = User(pullRequest.user.username, pullRequest.user.profilePhoto),
            body = pullRequest.body,
            createdAt = pullRequest.createdAt
    )
}