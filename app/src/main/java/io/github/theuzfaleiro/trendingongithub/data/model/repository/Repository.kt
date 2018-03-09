package io.github.theuzfaleiro.trendingongithub.data.model.repository


data class Repository(val name: String,
                      val description: String,
                      val owner: Owner,
                      val starCount: Int,
                      val forkCount: Int)