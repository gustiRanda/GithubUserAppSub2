package com.gmind.githubuserapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id : Int,
    val login : String,
    val avatar_url : String
) : Parcelable
