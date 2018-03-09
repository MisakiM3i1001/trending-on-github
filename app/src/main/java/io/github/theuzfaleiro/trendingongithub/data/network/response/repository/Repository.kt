package io.github.theuzfaleiro.trendingongithub.data.network.response.repository

import com.squareup.moshi.Json
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Owner


data class Repository(@Json(name = "name") val name: String,
                      @Json(name = "description") val description: String,
                      @Json(name = "owner") val owner: Owner,
                      @Json(name = "stargazers_count") val starCount: Int,
                      @Json(name = "forks_count") val forkCount: Int)