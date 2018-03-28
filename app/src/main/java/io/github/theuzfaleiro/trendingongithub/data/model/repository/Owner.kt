package io.github.theuzfaleiro.trendingongithub.data.model.repository

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Owner(val userName: String, val avatarUrl: String) : Parcelable