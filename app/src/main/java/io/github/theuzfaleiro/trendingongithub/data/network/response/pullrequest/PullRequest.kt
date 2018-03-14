package io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest

import com.squareup.moshi.Json

data class PullRequest(@Json(name = "html_url") val htmlUrl: String, @Json(name = "title") val title: String, @Json(name = "user") val user: User, @Json(name = "body") val body: String, @Json(name = "created_at") val createdAt: String)