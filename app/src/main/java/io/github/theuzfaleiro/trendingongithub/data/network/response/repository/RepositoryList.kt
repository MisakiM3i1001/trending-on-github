package io.github.theuzfaleiro.trendingongithub.data.network.response.repository

import com.squareup.moshi.Json

class RepositoryList(@Json(name = "items") val repositoryList: List<Repository> = listOf())