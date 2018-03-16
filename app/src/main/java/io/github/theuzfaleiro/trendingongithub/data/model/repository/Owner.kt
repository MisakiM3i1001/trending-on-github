package io.github.theuzfaleiro.trendingongithub.data.model.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(val userName: String, val avatarUrl: String) : Parcelable