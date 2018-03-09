package io.github.theuzfaleiro.trendingongithub.data.network.response.repository

import com.squareup.moshi.Json
import io.github.theuzfaleiro.trendingongithub.data.model.repository.Repository

data class RepositoryList(@Json(name = "items") val repositoryList: List<Repository> = listOf())