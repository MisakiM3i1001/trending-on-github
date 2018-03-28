package io.github.theuzfaleiro.trendingongithub.data.model.pullrequest

import android.annotation.SuppressLint
import android.os.Parcelable
import io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest.PullRequest
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class PullRequest(val htmlUrl: String,
                       val title: String,
                       val user: User,
                       val body: String,
                       val createdAt: String) : Parcelable {
    constructor(pullRequest: PullRequest) : this(
            htmlUrl = pullRequest.htmlUrl,
            title = pullRequest.title,
            user = User(pullRequest.user.username, pullRequest.user.profilePhoto),
            body = pullRequest.body,
            createdAt = pullRequest.createdAt
    )
}