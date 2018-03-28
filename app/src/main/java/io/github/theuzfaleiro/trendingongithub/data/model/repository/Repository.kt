package io.github.theuzfaleiro.trendingongithub.data.model.repository

import android.annotation.SuppressLint
import android.os.Parcelable
import io.github.theuzfaleiro.trendingongithub.data.network.response.repository.Repository
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.AdapterConstants
import io.github.theuzfaleiro.trendingongithub.ui.feature.common.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Repository(val name: String,
                      val description: String,
                      val owner: Owner,
                      val starCount: Int,
                      val forkCount: Int) : ViewType, Parcelable {

    override fun getViewType(): Int = AdapterConstants.REPOSITORY

    constructor(repository: Repository) : this(
            name = repository.name,
            description = repository.description,
            owner = Owner(repository.owner.userName, repository.owner.avatarUrl),
            starCount = repository.starCount,
            forkCount = repository.forkCount
    )
}