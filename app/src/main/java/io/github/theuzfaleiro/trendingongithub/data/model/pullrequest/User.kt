package io.github.theuzfaleiro.trendingongithub.data.model.pullrequest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val username: String, val profilePhoto: String) : Parcelable