package io.github.theuzfaleiro.trendingongithub.data.model.repository

data class Repository(private val name: String,
                      private val description: String,
                      private val owner: Owner,
                      private val starCount: Int,
                      private val forkCount: Int) {
    constructor(repository: Repository) : this(
            name = repository.name,
            description = repository.description,
            owner = repository.owner,
            starCount = repository.starCount,
            forkCount = repository.forkCount
    )
}