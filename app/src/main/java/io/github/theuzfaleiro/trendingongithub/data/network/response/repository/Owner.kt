package io.github.theuzfaleiro.trendingongithub.data.network.response.repository

import com.squareup.moshi.Json

data class Owner(@Json(name = "login") val userName: String,
                 @Json(name = "avatar_url") val avatarUrl: String)