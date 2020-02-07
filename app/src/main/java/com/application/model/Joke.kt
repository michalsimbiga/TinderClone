package com.application.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Joke(
    @SerializedName("id") val id: Int,
    @SerializedName("joke") val text: String
) : Parcelable