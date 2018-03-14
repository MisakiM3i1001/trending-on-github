package io.github.theuzfaleiro.trendingongithub.data.network.response.pullrequest

import com.squareup.moshi.Json

data class User(@Json(name = "login") val username: String, @Json(name = "avatar_url") val profilePhoto: String)