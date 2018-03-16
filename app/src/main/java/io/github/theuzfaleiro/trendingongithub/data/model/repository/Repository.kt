package io.github.theuzfaleiro.trendingongithub.data.model.repository

import android.os.Parcelable
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(val name: String,
                      val description: String,
                      val owner: Owner,
                      val starCount: Int,
                      val forkCount: Int) : Parcelable {
    constructor(repository: Repository)  : this(
            name = repository.name,
            description = repository.description,
            owner = Owner(repository.owner.userName, repository.owner.avatarUrl),
            starCount = repository.starCount,
            forkCount = repository.forkCount
    )
}