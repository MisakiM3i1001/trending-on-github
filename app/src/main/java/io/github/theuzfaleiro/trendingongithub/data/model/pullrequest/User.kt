package io.github.theuzfaleiro.trendingongithub.data.model.pullrequest

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class User(val username: String, val profilePhoto: String) : Parcelable